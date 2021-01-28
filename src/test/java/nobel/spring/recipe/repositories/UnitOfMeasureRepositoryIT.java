package nobel.spring.recipe.repositories;

import nobel.spring.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository uomRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByUnit() {
        Optional<UnitOfMeasure> uomOptional = uomRepository.findByUnit("Cup");
        assertEquals("Cup", uomOptional.get().getUnit());
    }
}