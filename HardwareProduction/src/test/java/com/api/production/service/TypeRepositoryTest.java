package com.api.production.service;

import com.api.production.DAO.TypeDAO;
import com.api.production.model.TypeEntity;
import com.api.production.service.impl.TypeServiceImpl;
import com.api.production.wrapper.TypeWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class TypeRepositoryTest {

    @Mock
    private TypeDAO typeDAO;
    @InjectMocks
    private TypeServiceImpl typeService;
    Map<String, String> typeToSave = new HashMap<>();
    TypeWrapper typeSaved = null;

    @BeforeEach
    void setup() {
        typeToSave.put("name", "Headphone");
        typeService.saveType(typeToSave);
    }

    @DisplayName("Test to add type")
    @Test
    public void addTypeTest() {
        //given
        typeToSave = new HashMap<>();
        typeToSave.put("name", "Keyboard");
        //when
        ResponseEntity<String> typeSaved = typeService.saveType(typeToSave);
        //then
        log.warn("{}", typeDAO.getAll());
        assertThat(typeSaved).isNotNull();
    }

    @DisplayName("Test to add type with throw exception")
    @Test
    public void addTypeExceptionTest() {
        //given
        typeService.saveType(typeToSave);
        TypeEntity entity= new TypeEntity();
        BeanUtils.copyProperties(typeSaved, entity);
        given(typeDAO.findById(typeSaved.getId())).willReturn(Optional.of(entity));
        //when & then
        assertThrows(DataIntegrityViolationException.class, () -> {
            typeService.saveType(typeToSave);
        });
        //then
        verify(typeDAO, never()).save(any(TypeEntity.class));
    }
}
