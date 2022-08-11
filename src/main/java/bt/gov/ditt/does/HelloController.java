package bt.gov.ditt.does;

import bt.gov.ditt.does.Dto.*;
import bt.gov.ditt.does.Entity.Token;
import bt.gov.ditt.does.Service.APIService;
import bt.gov.ditt.does.Service.ICommonService;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wso2.client.model.G2C_CommonBusinessAPI.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/")

public class HelloController {

    @Autowired
    ICommonService commonService;

    @Autowired
    private APIService api;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model, HttpServletRequest request)
    {
        Role currentRole = null;
        UserRolePrivilegeDES dto = new UserRolePrivilegeDES();
        int userRoleId = 0;
        String LocationId = "", uid = null;
        String applicationCount = null;
        HttpSession session = request.getSession();
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String userRolePrivilegeEndPointUrl =resourceBundle1.getString("getUserRolePrivilege.endPointURL");
        try {
            if(request.getUserPrincipal()!=null && request.getUserPrincipal().getName()!=null)
            {
                uid = request.getUserPrincipal().getName();
                request.setAttribute("userId", uid);
                OkHttpClient httpClient = new OkHttpClient();
                httpClient.setConnectTimeout(100, TimeUnit.SECONDS);
                httpClient.setReadTimeout(100, TimeUnit.SECONDS);

                org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
                apiClient.setHttpClient(httpClient);
                apiClient.setBasePath(userRolePrivilegeEndPointUrl);
                Token token = api.getApplicationToken();
                apiClient.setAccessToken(token.getAccess_token());

             //   apiClient.setAccessToken("1a1e7391-07c4-3763-b4eb-2c30c810d80d");

                org.wso2.client.api.G2C_CommonBusinessAPI.DefaultApi api = new org.wso2.client.api.G2C_CommonBusinessAPI.DefaultApi(apiClient);
                UserRolePrivilegeHierarchyResponse userRolePrivilegeResponse = api.userroleprivilegehierarchyUseridDeptshortcodeGet(uid, "M_MOWHS.D_DOES");
                UserRolePrivilegeHierarchyObj userRolePrivilegeObj = null;
                if (userRolePrivilegeResponse.getUserRolePrivilegeHierarchyResponse().getUserRolePrivilegeHierarchy() != null
                        && !userRolePrivilegeResponse.getUserRolePrivilegeHierarchyResponse().getUserRolePrivilegeHierarchy().isEmpty()) {
                    userRolePrivilegeObj = userRolePrivilegeResponse.getUserRolePrivilegeHierarchyResponse().getUserRolePrivilegeHierarchy().get(0);

                    dto.setFullName(userRolePrivilegeObj.getFullName());
                    dto.setCid(userRolePrivilegeObj.getCid());
                    dto.setMobileNo(userRolePrivilegeObj.getMobile());
                    dto.setEmailId(userRolePrivilegeObj.getEmail());
                    dto.setUserType(userRolePrivilegeObj.getUserType());
                    dto.setTelephoneNo(userRolePrivilegeObj.getTelephone());

                    List<Role> roles = new ArrayList<Role>();
                    UserRolesObj userRoleList = userRolePrivilegeObj.getUserRoles();

                    for (int i = 0; i < userRoleList.getUserRole().size(); i++)
                    {
                        UserRoleObj userRole = userRoleList.getUserRole().get(i);
                        int roleId = userRole.getRoleId();
                        String roleName = userRole.getRoleName();
                        String roleDescription = userRole.getRoleDescription();

                        Role role = new Role();
                        userRoleId = roleId;
                        role.setRoleId(Integer.toString(roleId));
                        role.setRoleCode(roleDescription);
                        role.setRoleName(roleName);
                        List<Service> services = new ArrayList<Service>();
                        DeptServicesObj deptServicesObj = userRole.getDeptServices();
                        for (int j = 0; j < deptServicesObj.getDeptService().size(); j++)
                        {
                            DeptServiceObj deptServiceObj = deptServicesObj.getDeptService().get(j);
                            Service service = new Service();

                            List<Privilege> privileges = new ArrayList<Privilege>();
                            ServicePrivilegesObj servicePrivilegesObj = deptServiceObj.getServicePrivileges();
                            for (int k = 0; k < servicePrivilegesObj.getServicePrivilege().size(); k++)
                            {
                                ServicePrivilegeObj servicePrivilegeObj = servicePrivilegesObj.getServicePrivilege().get(k);
                                Privilege privilege = new Privilege();
                                privilege.setPrivilegeId(Integer.toString(servicePrivilegeObj.getPrivilegeId()));
                                privilege.setPrivilegeCode(servicePrivilegeObj.getPrivilegeDescription());
                                privilege.setPrivilegeName(servicePrivilegeObj.getPrivilegeName());
                                privileges.add(privilege);
                            }
                            service.setPrivileges(privileges.toArray(new Privilege[privileges.size()]));
                            services.add(service);
                        }

                        role.setServices(services.toArray(new Service[services.size()]));
                        roles.add(role);
                    }

                    dto.setRoles(roles.toArray(new Role[roles.size()]));
                    if (roles != null && roles.size() == 1) {
                        dto.setCurrentRole(roles.get(0));
                    } else if (roles.size() > 1) {
                        currentRole = roles.get(1);
                        dto.setCurrentRole(currentRole);
                    }

                    List<Jurisdiction> jurisdictions = new ArrayList<Jurisdiction>();
                    JurisdictionsObj jurisdictionsObj = userRolePrivilegeObj.getJurisdictions();
                    for (int k = 0; k < jurisdictionsObj.getJurisdiction().size(); k++) {
                        JurisdictionObj jurisdictionObj = jurisdictionsObj.getJurisdiction().get(k);
                        Jurisdiction jurisdiction = new Jurisdiction();
                        jurisdiction.setJurisdiction(Integer.toString(jurisdictionObj.getJurisdictionId()));
                        jurisdiction.setJurisdictionType(jurisdictionObj.getJurisdiction());
                        jurisdiction.setLocationID(Integer.toString(jurisdictionObj.getLocationId()));
                        jurisdictions.add(jurisdiction);
                    }

                    dto.setJurisdictions(jurisdictions.toArray(new Jurisdiction[jurisdictions.size()]));
                    for (int m = 0; m < dto.getRoles().length; m++) {
                        for (int i = 0; i < dto.getRoles()[m].getServices().length; i++) {
                            Service svc = dto.getCurrentRole().getServices()[i];
                            for (int j = 0; j < svc.getPrivileges().length; j++) {
                                Privilege priv = svc.getPrivileges()[j];
                                System.out.println("role name : " + dto.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")");
                            }
                        }
                    }
                        for (int n = 0; n < dto.getJurisdictions().length; n++) {
                            LocationId = dto.getJurisdictions()[n].getLocationID();
                            System.out.println("Jurisdiction Id: "
                            + dto.getJurisdictions()[n].getJurisdiction() + " Jurisdiction Type:  "
                            + dto.getJurisdictions()[n].getJurisdictionType() + " Location Id: "
                            + dto.getJurisdictions()[n].getLocationID());
                        }
                    session.setAttribute("UserRolePrivilege", dto);
                    if (dto.getCurrentRole().getRoleName() != "" && dto.getJurisdictions()[0].getLocationID() != "") {
                        if (dto.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("1")){
                            model.addAttribute("totalApplication0", commonService.countApplications(LocationId, "ArchitectCount"));
                            model.addAttribute("totalApplication1", commonService.countApplications(LocationId, "ElectricalCount"));
                            model.addAttribute("totalApplication2", commonService.countApplications(LocationId, "StructuralCount"));
                            model.addAttribute("totalApplication6", commonService.countApplications(LocationId, "DzoOperatorCount"));

                        } else{
                            model.addAttribute("totalApplication3", commonService.countApplications(LocationId, "DESarchitectCount"));
                            model.addAttribute("totalApplication4", commonService.countApplications(LocationId, "DESelectricalCount"));
                            model.addAttribute("totalApplication5", commonService.countApplications(LocationId, "DESstructuralCount"));
                            model.addAttribute("totalApplication7", commonService.countApplications(LocationId, "DesOperatorCount"));
                        }
                        model.addAttribute("message", "Welcome to Government to citizen service delivery initiative");
                    }
                    return "hello";
                }
            } else {
                return "login";
            }
            return "login";
        } catch (Exception e) {
            System.out.println("Error at GetUserDetails[doAction]" + e);
        }
        return "Error";
    }
}