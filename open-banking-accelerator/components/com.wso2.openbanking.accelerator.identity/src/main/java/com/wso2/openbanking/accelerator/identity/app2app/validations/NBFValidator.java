/**
 * Copyright (c) 2024, WSO2 LLC. (https://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.wso2.openbanking.accelerator.identity.app2app.validations;

import com.wso2.openbanking.accelerator.common.util.JWTUtils;
import com.wso2.openbanking.accelerator.identity.app2app.model.AppAuthValidationJWT;
import com.wso2.openbanking.accelerator.identity.app2app.validations.annotations.ValidateNBF;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validation class for validating NBF of a JWT.
 */
public class NBFValidator implements ConstraintValidator<ValidateNBF, AppAuthValidationJWT> {

    private static final long DEFAULT_TIME_SKEW_IN_SECONDS = 300L;
    private static final Log log = LogFactory.getLog(NBFValidator.class);

    @Override
    public boolean isValid(AppAuthValidationJWT appAuthValidationJWT,
                           ConstraintValidatorContext constraintValidatorContext) {

        Date notValidBefore = appAuthValidationJWT.getNotValidBefore();
        return JWTUtils.validateNotValidBefore(notValidBefore, DEFAULT_TIME_SKEW_IN_SECONDS);

    }
}