/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;

/**
 *
 * @author tanuj_000
 */
public class uploadVideoBrief extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("Reached @ uploadVideoBrief");
            /*
             String base64video = request.getParameter("base64video");
             JSONObject videoObject = new JSONObject(base64video);
             String base64code = videoObject.getString("base64value");

             byte[] data = Base64.decodeBase64(base64code);
             try (OutputStream stream = new FileOutputStream(System.getenv("OPENSHIFT_DATA_DIR") + "/NPJS_files/uploadedvid.mp4")) {
             stream.write(data);
             } catch (Exception e) {
             e.printStackTrace();
             }

             out.println("base64code: " + base64code);
             */

            FileItem stringItem = null;
            FileItem fileItem = null;

            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    stringItem = item;
                    break;
                }
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    fileItem = item;
                    break;
                }
            }

            if (stringItem != null && fileItem != null) {
                String fileName = stringItem.getString();
                String filePath = System.getenv("OPENSHIFT_DATA_DIR") + "NPJS_files" + File.separator + fileName;
                out.println("filePath: " + filePath);
                File storeFile = new File(filePath);
                fileItem.write(storeFile);
            } else {
                out.println("stringItem or fileItem null.");
            }

        } catch (FileUploadException ex) {
            out.println("Error: FileUploadException");
            out.println(ex.getMessage());
            Logger.getLogger(uploadVideoBrief.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            out.println("Error: Exception");
            out.println(ex.getMessage());
            Logger.getLogger(uploadVideoBrief.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(uploadVideoBrief.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(uploadVideoBrief.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
