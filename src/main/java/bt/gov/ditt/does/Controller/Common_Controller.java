package bt.gov.ditt.does.Controller;

import bt.gov.ditt.does.Dto.*;
import bt.gov.ditt.does.Entity.OwnerEntity;
import bt.gov.ditt.does.Entity.Token;
import bt.gov.ditt.does.Service.APIService;
import bt.gov.ditt.does.Service.ICommonService;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wso2.client.model.G2C_CommonBusinessAPI.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pema Drakpa on 6/8/2020.
 */
@Controller
@RequestMapping("/common")
public class Common_Controller {


     @Autowired
    ICommonService commonService;

    @Autowired
    private APIService api;

    @RequestMapping(value ="/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model, @RequestParam("userId") String userId, @RequestParam("password") String pass) {
        HttpSession session = request.getSession();
        Role currentRole = null;
        UserRolePrivilegeDES dto = new UserRolePrivilegeDES();
        int userRoleId = 0;
        String LocationId = "", uid = userId;
        String applicationCount = null;
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String userRolePrivilegeEndPointUrl =resourceBundle1.getString("getUserRolePrivilege.endPointURL");
        try {
//            System.out.println("UserName" + request.getUserPrincipal().getName());
            if (uid!=null || uid!="") {
                request.setAttribute("userId", uid);
                OkHttpClient httpClient = new OkHttpClient();
                httpClient.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
                httpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);

                org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
                apiClient.setHttpClient(httpClient);

                apiClient.setBasePath(userRolePrivilegeEndPointUrl);
                 Token token = api.getApplicationToken();
                    apiClient.setAccessToken(token.getAccess_token());

               /* apiClient.setBasePath(userRolePrivilegeEndPointUrl);
                apiClient.setAccessToken("1a1e7391-07c4-3763-b4eb-2c30c810d80d");*/

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

                    for (int i = 0; i < userRoleList.getUserRole().size(); i++) {
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
                        for (int j = 0; j < deptServicesObj.getDeptService().size(); j++) {
                            DeptServiceObj deptServiceObj = deptServicesObj.getDeptService().get(j);
                            Service service = new Service();

                            List<Privilege> privileges = new ArrayList<Privilege>();
                            ServicePrivilegesObj servicePrivilegesObj = deptServiceObj.getServicePrivileges();
                            for (int k = 0; k < servicePrivilegesObj.getServicePrivilege().size(); k++) {
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
                        currentRole = roles.get(0);
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

                        }
                        else{
                            model.addAttribute("totalApplication3", commonService.countApplications(LocationId, "DESarchitectCount"));
                            model.addAttribute("totalApplication4", commonService.countApplications(LocationId, "DESelectricalCount"));
                            model.addAttribute("totalApplication5", commonService.countApplications(LocationId, "DESstructuralCount"));
                            model.addAttribute("totalApplication7", commonService.countApplications(LocationId, "DesOperatorCount"));
                        }
                        model.addAttribute("message", "Welcome to Government to citizen service delivery initiative");
                    }
                }
                return "hello";
            }
        }
        catch (Exception e)
       {
           session.setAttribute("UserRolePrivilege", dto);
            e.printStackTrace();
           // return  "redirect:/?parameter=LOGIN_FAILED";
           return "hello";
        }
        return null;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "expired", required = false) String expired) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "logout";
    }

    @RequestMapping(value = "/loadPage")
    public String loadPage(HttpServletRequest request, @RequestParam("parameter") String param, ModelMap map) {
        HttpSession sess = request.getSession();
        UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
        DropDownDto dzongkhag=commonService.getDzongkhagByUserLocation(userdet.getJurisdictions()[0].getLocationID());
        map.addAttribute("dzongkhagName", dzongkhag.getDropdownName());
        if(param.equalsIgnoreCase("tasklist"))
        {
            Integer Juris_type = Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction());
            Integer Juris_id = Integer.parseInt(userdet.getJurisdictions()[0].getLocationID());
            String user_role = userdet.getCurrentRole().getRoleName();
            List<TasklistDto> groupTasklist = commonService.getGroupTask(user_role, Juris_type, Juris_id);
            List<TasklistDto> myTasklist = commonService.getMyTask(user_role, Juris_type, Juris_id);
            map.addAttribute("groupTaskList", groupTasklist);
            map.addAttribute("myTasklist", myTasklist);
        }
        else
        {
            request.setAttribute("isPublic", "N");
            request.setAttribute("payment", "paymentOK");
            request.setAttribute("paymentStatus", "PAID");
            String message = request.getParameter("message");
            if(param.equalsIgnoreCase("buildingForm")) {
                List<DropDownDto> dzongkhagList = commonService.getDropdown("DZONGKHAG_LIST", "", "");
                map.addAttribute("dzongkhagList", dzongkhagList);

                /*DropDownDto dzongkhag=commonService.getDzongkhagByUserLocation(userdet.getJurisdictions()[0].getLocationID());
                map.addAttribute("dzongkhagName", dzongkhag.getDropdownName());*/

                List<DropDownDto> projectTitle = commonService.getDropdown("PROJECT_TITLE", "", "");
                map.addAttribute("projectTitle", projectTitle);

                List<DropDownDto> buidlingConstructionType = commonService.getDropdown("BUILDING_CONST_TYPE", "", "");
                map.addAttribute("buildingConstType", buidlingConstructionType);

                List<DropDownDto> buildingUse = commonService.getDropdown("BUILDING_USE", "", "");
                map.addAttribute("buildingUse", buildingUse);
            }
        }

        return param;
    }

    @RequestMapping(value ="/generateReport",method = RequestMethod.GET)
    public String generateReport(HttpServletRequest request) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
        String usr_Role = userdet.getCurrentRole().getRoleName();
        request.setAttribute("usr_Role", usr_Role);
        String from_Date = request.getParameter("fromDate");
        String to_Date = request.getParameter("toDate");

        request.setAttribute("reportList", commonService.getReport(usr_Role, from_Date, to_Date));
        return "reportList";
    }

    @ResponseBody
    @RequestMapping(value = "/populateDropdown")
    public List<DropDownDto> populateDropdown(HttpServletRequest request, @RequestParam("dropdownType") String dropdownType,@RequestParam("parameter") String param, ModelMap map) {
        List<DropDownDto> dropDownDTO = null;
        try {
            dropDownDTO =commonService.getDropdown(dropdownType, param, "");
        }
        catch (Exception e)
        {
            System.out.print(e);
        }
        return dropDownDTO;
    }

    @RequestMapping(value ="/claimTasklistApplication")
    public void claimTasklistApplication(HttpServletRequest request, @RequestParam("applicationNo") String applicationNo, @RequestParam("taskId") String taskId, ModelMap map) {
        try {
            HttpSession sess = request.getSession();
            UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
            String message = commonService.claimTasklistApplication(applicationNo,taskId,userdet.getFullName());
        }
        catch (Exception e)
        {
            System.out.print(e);
        }
    }

    @RequestMapping(value ="/unClaimTasklistApplication")
    public void unClaimTasklistApplication(HttpServletRequest request, @RequestParam("applicationNo") String applicationNo, @RequestParam("taskId") String taskId, ModelMap map)
    {
        try
        {
            HttpSession sess = request.getSession();
            UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
            String message = commonService.unClaimTasklistApplication(applicationNo, taskId, userdet.getFullName());
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @RequestMapping(value ="/unHoldTasklistApplication")
    public void unHoldTasklistApplication(HttpServletRequest request, @RequestParam("applicationNo") String applicationNo, @RequestParam("taskId") String taskId, ModelMap map)
    {
        try
        {
            HttpSession sess = request.getSession();
            UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
            String message = commonService.unHoldTasklistApplication(applicationNo, taskId, userdet.getFullName());
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

}
