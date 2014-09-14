package org.springframework.boot.issues.gh1539.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.issues.gh1539.model.TestModel;
import org.springframework.stereotype.Service;

/**
 * Test service.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
@Service
public class TestService {
    
    @Autowired
    protected ValidatorComponent<TestModel> validator;

    /**
     * Validation.
     * @param model TestModel
     */
    public void validate(TestModel model) {

        // JSR303 validation
        validator.validate(model);
    }

}
