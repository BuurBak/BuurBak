package com.buurbak.api.users.controller.specifcation;

import com.buurbak.api.users.model.Customer;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "deleted", constVal = "false", spec = Equal.class)
public interface NotDeletedCustomerSpecification extends Specification<Customer> {
}