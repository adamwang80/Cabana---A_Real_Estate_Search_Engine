package com.aic.cabana.spring.files.excel.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.aic.cabana.spring.files.excel.model.Cabana;
import com.aic.cabana.spring.files.excel.repository.CabanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aic.cabana.spring.files.excel.helper.ExcelHelper;

@Service
public class ExcelService {

  @Autowired
  CabanaRepository cabanaRepository;

  public void save(MultipartFile file) {
    try {
      List<Cabana> entries = ExcelHelper.excelToTutorials(file.getInputStream());
      cabanaRepository.saveAll(entries);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<Cabana> tutorials = cabanaRepository.findAll();
    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
    return in;
  }

  public List<Cabana> getAllAddresses() {
    return cabanaRepository.findAll();
  }
}
