package com.api.production.DAO;

import com.api.production.model.TypeEntity;
import com.api.production.wrapper.TypeWrapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TypeRepositoryTest {
  @Autowired private TypeDAO typeDAO;

  // @Rollback(value = false)
  @Test
  @DisplayName("Saving employee must be defined")
  @Order(1)
  void saveTypeTest() {
    TypeEntity type = TypeEntity.builder().type_name("Camera").build();
    TypeEntity typeSaved = typeDAO.save(type);

    assertNotNull(typeSaved);
    assertTrue(typeSaved.getType_id() > 0);
  }

  @Test
  @DisplayName("If the name is in the table not save")
  @Order(2)
  void saveNotValidIfNameExist() {
    TypeEntity type = TypeEntity.builder().type_name("CPU").build();

    assertThrows(
        DataIntegrityViolationException.class,
        () -> {
          typeDAO.save(type);
        },
        "Debería lanzarse una excepción de violación de integridad de datos al intentar guardar un tipo con nombre duplicado");
  }

  @Test
  @DisplayName("If exist type return type")
  @Order(3)
  public void getTypeByIdTest() {
    int id = 3;
    TypeWrapper type = typeDAO.getType(id);
    assertNotNull(type);
    assertThat(type.getName()).isEqualTo("Mouse");
  }

  @Test
  @DisplayName("If no exist type return false")
  @Order(4)
  public void noExistTypeIsNull() {
    int id = 100000;
    assertThat(typeDAO.existsById(id)).isFalse();
  }

  @Test
  @DisplayName("If exist type can be update")
  @Order(5)
  public void updateType() {
    TypeWrapper typeDB = typeDAO.getType(6);
    typeDB.setName("phone");
    TypeEntity typeToUpdate = TypeEntity.builder()
                                        .type_name(typeDB.getName())
                                        .type_id(typeDB.getId()).build();
    TypeEntity typeUpdated = typeDAO.save(typeToUpdate);
    assertThat(typeUpdated.getType_name()).isEqualTo("phone");
  }

  @Test
  @DisplayName("Return all types")
  @Order(6)
  public void getAllTypesTest() {
    List<TypeEntity> types = typeDAO.findAll();
    assertThat(types).size().isEqualTo(5);
  }

  @Test
  @DisplayName("Id exist DB can be delete")
  @Order(7)
  public void deleteTypeTest() {
    TypeEntity type = TypeEntity.builder().type_name("Camera").build();
    TypeEntity typeSaved = typeDAO.save(type);
    typeDAO.deleteById(typeSaved.getType_id());
    assertThat(typeDAO.existsById(typeSaved.getType_id())).isFalse();
  }
}
