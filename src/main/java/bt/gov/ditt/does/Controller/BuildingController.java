package bt.gov.ditt.does.Controller;

import bt.gov.ditt.does.Dto.*;
import bt.gov.ditt.does.Entity.OwnerEntity;
import bt.gov.ditt.does.Entity.Token;
import bt.gov.ditt.does.Service.APIService;
import bt.gov.ditt.does.Service.ICommonService;
import com.squareup.okhttp.OkHttpClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wso2.client.api.DCRC_CitizenDetailsAPI.DefaultApi;
import org.wso2.client.model.CDB_ArchitectDetailsAPI.ArchitectDetailOBJ;
import org.wso2.client.model.CDB_ArchitectDetailsAPI.ArchitectDetailResponse;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizenDetailsResponse;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizendetailsObj;
import org.wso2.client.model.RCSC_EmployeeDetailsAPI.EmployeeDetailsOBJ;
import org.wso2.client.model.RCSC_EmployeeDetailsAPI.EmployeeDetailsResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tandin on 6/10/2020.
 */
@Controller
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    ICommonService commonService;

    @Autowired
    private APIService api;

    @RequestMapping(value = "/saveApplication", method = RequestMethod.POST)
    public String saveApplication(@RequestParam("type") String type,BuildingDto dto, HttpServletRequest request, ModelMap map){
        try {
            String result = commonService.saveApplication(type,dto, request);
            if (result.equalsIgnoreCase("Failed")) {
                map.addAttribute("result", result);
            } else {
                map.addAttribute("result", "SUBMITTED");
                map.addAttribute("applicationNo", result);
            }
        } catch (Exception ex) {
            System.out.print(ex);
            return "" + ex;
        }
        return "message";
    }

    @RequestMapping(value = "/rejectApplication", method = RequestMethod.POST)
    public String rejectApplication(@RequestParam("type") String type, BuildingDto dto, HttpServletRequest request, ModelMap map) {
        try {
            String result = null;
            if (type.equalsIgnoreCase("OPERATOR_FORWARD_TO_DES")) {
                dto.setApplicationType(type);
                result = commonService.saveApplication(type, dto, request);
                dto.setApplication_Id(result);
                map.addAttribute("application_id", result);

            }
            else if(type.equalsIgnoreCase("HOLD")){
                dto.setApplicationType(type);
                result = commonService.rejectApplication(dto, request, type);
                map.addAttribute("result", type);
                map.addAttribute("result", "HOLD");
                return "message";
            }
            else if(type.equalsIgnoreCase("UNHOLD")){
                dto.setApplicationType(type);
                result = commonService.rejectApplication(dto, request, type);
            }

            else if(type.equalsIgnoreCase("REJECT")){
                dto.setApplicationType(type);
                result = commonService.rejectApplication(dto, request, type);
                map.addAttribute("result", type);
                map.addAttribute("result", "REJECT");
                return "message";
            }
            else if(type.equalsIgnoreCase("FORWARD")){
                dto.setApplicationType(type);
                result = commonService.rejectApplication(dto, request, type);
                dto.setApplication_Id(result);
                map.addAttribute("application_id", result);
                map.addAttribute("result", "FORWARD");
                return "message";
            }
            else if (type.equalsIgnoreCase("DZONGKHAG_TO_DES")) {
                dto.setApplicationType(type);
                result = commonService.rejectApplication(dto, request, type);
                dto.setApplication_Id(result);
                map.addAttribute("application_id", result);
                map.addAttribute("result", "FORWARD");
                return "message";
            }
            else if (type.equalsIgnoreCase("FORWARD_DRAWING")) {
                dto.setApplicationType(type);
                result = commonService.rejectApplication(dto, request, type);
                dto.setApplication_Id(result);
                map.addAttribute("application_id", result);
                map.addAttribute("result", "FORWARD");
                return "message";
            }
            else if (type.equalsIgnoreCase("FORWARD_DRAWING_TO_DES")) {
                dto.setApplicationType(type);
                result = commonService.rejectApplication(dto, request, type);
                dto.setApplication_Id(result);
                map.addAttribute("application_id", result);
                map.addAttribute("result", "FORWARD");
                return "message";
            }
            else if (type.equalsIgnoreCase("DZO_TO_DES")) {
                dto.setApplicationType(type);
                result = commonService.rejectApplication(dto, request, type);
                dto.setApplication_Id(result);
                map.addAttribute("application_id", result);
                map.addAttribute("result", "FORWARD");
                return "message";
            }
            else{
                dto.setApplicationType(type);
                result = commonService.rejectApplication(dto, request, type);
                dto.setApplication_Id(result);
                map.addAttribute("application_id", result);
                map.addAttribute("result", "FORWARD");
                return "message";
            }
            result = commonService.rejectApplication(dto, request, type);
            map.addAttribute("result", type);
        } catch (Exception ex) {
            System.out.print(ex);
            return "" + ex;
        }

        return "message";
    }
    @RequestMapping(value = "/architectVerification", method = RequestMethod.POST)
    public String architectVerification(@RequestParam("verifierType") String verifierType, BuildingDto dto, HttpServletRequest request, ModelMap map) {
        try {
            String result = commonService.verfication(dto,verifierType, request);
            if (dto.getTaskId().equalsIgnoreCase("5")) {
                map.addAttribute("result", "Approved");
                return "message";
            } else if (verifierType.equalsIgnoreCase("ELECTRICAL_FORWARD")) {
                request.setAttribute("isElectricalForward", "Forwarded");
                return null;
            } else if (verifierType.equalsIgnoreCase("STRUCTURAL_FORWARD")) {
                request.setAttribute("isStructuralForward", "Forwarded");
                return null;
            }
            else if(verifierType.equalsIgnoreCase("SUBMIT"))
            {
                map.addAttribute("result", "SUBMIT");
                return "message";
            }
            else if(verifierType.equalsIgnoreCase("CLOSE"))
            {
                map.addAttribute("result", "CLOSE");
                return "message";
            }
            else if (verifierType.equalsIgnoreCase("Y"))
            {
                map.addAttribute("result", "Verified");
                return "message";
            }
            else {
                map.addAttribute("result", "Failed");
                return "message";
            }
        } catch (Exception ex)
        {
            System.out.print(ex);
            return "" + ex;
        }
    }

    @RequestMapping(value = "/openTasklistApplication", method = RequestMethod.GET)
    public String openTasklistApplication(HttpServletRequest request, ModelMap model)
    {
        String applicationNo = request.getParameter("applicationNo");
        String taskId = request.getParameter("taskId");
        try
        {
            HttpSession sess = request.getSession();
            UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");

            model.addAttribute("applicationDetails", commonService.openTasklistApplication(applicationNo, taskId, "BUILDING_DETAILS"));
            request.setAttribute("applicationDetails", commonService.openTasklistApplication(applicationNo, "16", "IS_ARCHIECT_DRAWING"));
            request.setAttribute("applicationDetails", commonService.openTasklistApplication(applicationNo, "16", "IS_STRUCTURAL_DRAWING"));
            request.setAttribute("applicationDetails", commonService.openTasklistApplication(applicationNo, "16", "IS_ELECTRICAL_DRAWING"));
            request.setAttribute("is_electrical_forwarded", commonService.openTasklistApplication(applicationNo, "", "IS_ELECTRICAL_FORWARDED"));
            request.setAttribute("is_structural_forwarded", commonService.openTasklistApplication(applicationNo, "", "IS_STRUCTURAL_FORWARDED"));
            request.setAttribute("statusCount", commonService.getTaskStage(applicationNo, taskId, "STATUS"));
            request.setAttribute("checkHold", commonService.getHoldStatus(applicationNo, taskId, "HOLDSTATUS"));
            request.setAttribute("documents", commonService.getDocuments(applicationNo));

            List<OwnerEntity> ownerList = commonService.getApplicationOwnerList(applicationNo);
            model.addAttribute("ownerList", ownerList);
            model.addAttribute("ownerPhoneNumber", ownerList);
            model.addAttribute("userRole", userdet.getCurrentRole().getRoleName());
            model.addAttribute("applicationNo", applicationNo);
            model.addAttribute("taskId", taskId);
            List<DropDownDto> reasonList = commonService.getDropdown("REASON", "", "");
            model.addAttribute("reasonList", reasonList);

            if (taskId.equalsIgnoreCase("3") || taskId.equalsIgnoreCase("15") || taskId.equalsIgnoreCase("4") || taskId.equalsIgnoreCase("5"))
            {
                model.addAttribute("plannerVerificationDtls", commonService.openTasklistApplication(applicationNo, taskId, "PLANNER_DETAILS"));
            }

            if (taskId.equalsIgnoreCase("16") || taskId.equalsIgnoreCase("17") || taskId.equalsIgnoreCase("18") || taskId.equalsIgnoreCase("19") || taskId.equalsIgnoreCase("20") || taskId.equalsIgnoreCase("21") || taskId.equalsIgnoreCase("26") || taskId.equalsIgnoreCase("23")) {
                model.addAttribute("FORWARD_REASON", commonService.openTasklistApplication(applicationNo, taskId, "FORWARD_REASON"));
            }

        } catch (Exception e)
        {
            System.out.print(e);
        }
        return "buildingVerificationPage";
    }


    @RequestMapping(value = "/trackApplication", method = RequestMethod.GET)
    public String trackApplication(HttpServletRequest request, ModelMap model, @RequestParam("applicationNo") String applicationNo) {
        try {
            model.addAttribute("applicationStatus", commonService.applicationStatus(applicationNo));
        } catch (Exception e) {
            System.out.print(e);
        }
        return "applicationStatus";
    }

    @ResponseBody
    @RequestMapping(value = "/getCitizenDetails")
    public OwnerDetailsDto citizenRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OwnerDetailsDto dto = new OwnerDetailsDto();
        CitizendetailsObj citizendetailsObj = null;
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String dcrcCitizenEndPointUrl = resourceBundle1.getString("getCitizenDetails.endPointURL");

        try {
            OkHttpClient httpClient = new OkHttpClient();
            httpClient.setConnectTimeout(100, TimeUnit.SECONDS);
            httpClient.setReadTimeout(100, TimeUnit.SECONDS);

            org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
            apiClient.setHttpClient(httpClient);
            apiClient.setBasePath(dcrcCitizenEndPointUrl);

            Token token = api.getApplicationToken();
            apiClient.setAccessToken(token.getAccess_token());

            DefaultApi api = new DefaultApi(apiClient);
            CitizenDetailsResponse citizenDetailsResponse = api.citizendetailsCidGet(request.getParameter("cidNo"));
            if (citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail() != null && !citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().isEmpty()) {
                citizendetailsObj = citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().get(0);
                dto.setOwnerCid(citizendetailsObj.getCid());
                dto.setOwnerName(citizendetailsObj.getFirstName() + " " + citizendetailsObj.getMiddleName() + " " + citizendetailsObj.getLastName());
                dto.setOwnerName(dto.getOwnerName().replaceAll("null ", ""));
                dto.setOwnerContact(citizendetailsObj.getMobileNumber());
            }
        } catch (Exception e) {
            System.out.println("Error at GetCitizenDetails[doAction]" + e);
        }
        return dto;

    }

    @ResponseBody
    @RequestMapping(value = "/getArchitectController")
    public ArchitectDetailsDto getArchitectDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArchitectDetailsDto dto = new ArchitectDetailsDto();
        ArchitectDetailOBJ architectDetailOBJ = null;
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String cdbArchitectEndPointUrl = resourceBundle1.getString("getCDBArchitectDetails.endPointURL");

        try {
            OkHttpClient httpClient = new OkHttpClient();
            httpClient.setConnectTimeout(100, TimeUnit.SECONDS);
            httpClient.setReadTimeout(100, TimeUnit.SECONDS);


            org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
            apiClient.setHttpClient(httpClient);
            apiClient.setBasePath(cdbArchitectEndPointUrl);

            Token token = api.getApplicationToken();
            apiClient.setAccessToken(token.getAccess_token());

            org.wso2.client.api.CDB_ArchitectDetailsAPI.DefaultApi API = new org.wso2.client.api.CDB_ArchitectDetailsAPI.DefaultApi(apiClient);

            String arn = request.getParameter("cidNo");
            ArchitectDetailResponse architectDetailResponse = API.architectArnGet(arn);
            if (architectDetailResponse.getArchitects().getArchitect() != null && !architectDetailResponse.getArchitects().getArchitect().isEmpty()) {
                architectDetailOBJ = architectDetailResponse.getArchitects().getArchitect().get(0);
                dto.setArchitectRegNo(architectDetailOBJ.getCIDNo());
                dto.setFullName(architectDetailOBJ.getName());
            }
        } catch (Exception e) {

            System.out.println("Error at GetCDBArchitectDetails[doAction]" + e);
        }
        return dto;

    }

    @ResponseBody
    @RequestMapping(value = "/getEmployeeDetails")
    public EngineeringDetailsDto getRCSCDetailsOnCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EngineeringDetailsDto dto = new EngineeringDetailsDto();
        CitizendetailsObj citizendetailsObj = null;

        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String dcrcCitizenEndPointUrl = resourceBundle1.getString("getCitizenDetails.endPointURL");

        try {
            OkHttpClient httpClient = new OkHttpClient();
            httpClient.setConnectTimeout(100, TimeUnit.SECONDS);
            httpClient.setReadTimeout(100, TimeUnit.SECONDS);

            org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
            apiClient.setHttpClient(httpClient);

            apiClient.setBasePath(dcrcCitizenEndPointUrl);

            Token token = api.getApplicationToken();
            apiClient.setAccessToken(token.getAccess_token());

            DefaultApi api = new DefaultApi(apiClient);
            CitizenDetailsResponse citizenDetailsResponse = api.citizendetailsCidGet(request.getParameter("cidNo"));
            if (citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail() != null && !citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().isEmpty()) {
                citizendetailsObj = citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().get(0);
                dto.setEmployee_ID(citizendetailsObj.getCid());
                dto.setName(citizendetailsObj.getFirstName() + " " + citizendetailsObj.getMiddleName() + " " + citizendetailsObj.getLastName());
                dto.setName(dto.getName().replaceAll("null ", ""));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at GetCitizenDetails[doAction]" + e);
        }
        return dto;
    }
    @RequestMapping(value="/loadpagetoemptylayout/donwloadFiles",method = RequestMethod.GET)
    public String downloadFiles(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
        String uploadDocId = request.getParameter("uuid");
        try{
            ApplicationDocDto doc = commonService.getDocumentDetailsByDocId(uploadDocId);
            byte[] fileContent = downloadFile(doc.getDocument_path());

                if(doc.getDocument_name().substring(doc.getDocument_name().length()-3).equalsIgnoreCase("JPG")||doc.getDocument_name().substring(doc.getDocument_name().length()-4).equalsIgnoreCase("jpeg") || doc.getDocument_name().substring(doc.getDocument_name().length()-3).equalsIgnoreCase("png")){
                    response.setContentType("image/jpeg");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocument_name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocument_name().substring(doc.getDocument_name().length()-3).equalsIgnoreCase("pdf")){
                    response.setContentType("APPLICATION/PDF");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocument_name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocument_name().substring(doc.getDocument_name().length()-4).equalsIgnoreCase("docx")){
                    response.reset();
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.setHeader("Content-Disposition", "inline;filename="+doc.getDocument_name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocument_name().substring(doc.getDocument_name().length()-3).equalsIgnoreCase("xls")){
                    response.setContentType("APPLICATION/vnd.ms-excel");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocument_name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocument_name().substring(doc.getDocument_name().length()-4).equalsIgnoreCase("xlsx")){
                    response.setContentType("Application/x-msexcel");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocument_name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else{
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-disposition", "attachment; filename="+doc.getDocument_name());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }

        }catch (Exception e){
            System.out.print(e);
            return  ""+e;
        }
        return null;
    }
    public static byte[] downloadFile(String uploadUlr) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(uploadUlr);
        return IOUtils.toByteArray(fileInputStream);
    }

}
