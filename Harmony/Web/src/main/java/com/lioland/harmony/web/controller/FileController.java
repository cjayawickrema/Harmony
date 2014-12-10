/*
 * Copyright (C) 2014 Chandima
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.lioland.harmony.web.controller;

import com.lioland.harmony.web.dao.FileMeta;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * @author Chandima
 */
@Controller
public class FileController {

    private LinkedList<FileMeta> files = new LinkedList<FileMeta>();
    private FileMeta fileMeta = null;

    /**
     * *************************************************
     * URL: /rest/controller/upload upload(): receives files
     *
     * @param request : MultipartHttpServletRequest auto passed
     * @param response : HttpServletResponse auto passed
     * @return LinkedList<FileMeta> as json format
     * **************************************************
     */
    @RequestMapping(value = "/file-upload", method = RequestMethod.POST)
    public @ResponseBody
    LinkedList<FileMeta> upload(HttpServletRequest req, HttpServletResponse response) {
        MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
        //1. build an iterator
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf = null;

        //2. get each file
        while (itr.hasNext()) {

            //2.1 get next MultipartFile
            mpf = request.getFile(itr.next());
            System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());

            //2.2 if files > 10 remove the first from the list
            if (files.size() >= 10) {
                files.pop();
            }

            //2.3 create new fileMeta
            fileMeta = new FileMeta();
            fileMeta.setFileName(mpf.getOriginalFilename());
            fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
            fileMeta.setFileType(mpf.getContentType());

            try {
                fileMeta.setBytes(mpf.getBytes());
                File folder = new File("D:\\GitHub\\Harmony\\Harmony\\Web\\src\\main\\webapp\\resources\\files");
                FileUtils.forceMkdir(folder);
                FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(new File(folder, mpf.getOriginalFilename())));

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //2.4 add to files
            files.add(fileMeta);
        }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;
    }

    /**
     * *************************************************
     * URL: /rest/controller/get/{value} get(): get file as an attachment
     *
     * @param response : passed by the server
     * @param value : value from the URL
     * @return void **************************************************
     */
    @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
    public void get(HttpServletResponse response, @PathVariable String value) {
        FileMeta getFile = files.get(Integer.parseInt(value));
        try {
            response.setContentType(getFile.getFileType());
            response.setHeader("Content-disposition", "attachment; filename=\"" + getFile.getFileName() + "\"");
            FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
