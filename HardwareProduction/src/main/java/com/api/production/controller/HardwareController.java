package com.api.production.controller;

import com.api.production.DAO.HardwareDAO;
import com.api.production.constants.ResponseConstants;
import com.api.production.model.ExcelReport;
import com.api.production.model.HardwareEntity;
import com.api.production.model.PdfEntity;
import com.api.production.service.HardwareService;
import com.api.production.utils.ResponseUtils;
import com.api.production.wrapper.HardwareWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/hardware")
@CrossOrigin(origins = "http://localhost:5173")
public class HardwareController {
    @Autowired
    private HardwareService hardwareService;
    @Autowired
    private HardwareDAO hardwareDAO;

    @PostMapping("/add")
    public ResponseEntity<String> addHardware(@RequestBody(required = true)Map<String, Object> reqMap) {
        try {
            return hardwareService.addHardware(reqMap);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/list/{id}")
    public ResponseEntity<HardwareWrapper> getHardware(@PathVariable(name = "id") Integer id) {
        try {
            return hardwareService.getHardware(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new HardwareWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<HardwareWrapper>> listAllHardware() {
        try {
            return hardwareService.getAllHardware();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateHadrware(@PathVariable(name = "id") Integer id, @RequestBody Map<String, Object> reqMap) {
        try {
            return hardwareService.updateHardware(id, reqMap);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHardware(@PathVariable(name = "id") Integer id) {
        try {
            return hardwareService.deleteHardware(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseUtils.getResponseEntity(ResponseConstants.SOME_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/list/byType/{id}")
    public ResponseEntity<List<HardwareWrapper>> getAnyByType(@PathVariable(name = "id") Integer id) {
        try {
            return hardwareService.findByType(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/print/pdf")
    public void generatePdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:sss");
        String currentDateFormat = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=hardware" + currentDateFormat + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<HardwareWrapper> hardwareWrapperList = hardwareDAO.getAll();
        PdfEntity pdfEntity = new PdfEntity(hardwareWrapperList);
        pdfEntity.export(response);
    }

    @GetMapping("/print/excel")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:sss");
        String currentDateFormat = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=hardware" + currentDateFormat + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<HardwareWrapper> hardwareWrapperList = hardwareDAO.getAll();
        ExcelReport excelReport = new ExcelReport(hardwareWrapperList);
        excelReport.exoort(response);
    }

    @GetMapping("list/search")
    public ResponseEntity<List<HardwareWrapper>> search(@Param("keyword") String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page - 1, size);
            if(keyword == null) {
                keyword = "";
            }
            keyword = keyword.concat("%");
            return new ResponseEntity<>(hardwareDAO.searchPaging(keyword, paging), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/total/quantity")
    public ResponseEntity<Integer> totalQuantity() {
        try {
             return new ResponseEntity<>(hardwareDAO.totalQuantity(), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 }
