package com.lewis.in28minsrestfulwebservices.controller;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.lewis.in28minsrestfulwebservices.model.SomeModel;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filterModel(){
        SomeModel someModel = new SomeModel("value1", "value2", "value3");
        return getMappingJacksonValue(someModel, "field3", "field2");
    }

    @GetMapping("/filtering/list")
    public MappingJacksonValue someModelList(){
        List<SomeModel> someModels = Arrays.asList(new SomeModel("value1", "value2", "value3"), new SomeModel("value21", "value22", "value23"));
       return getMappingJacksonValue(someModels, "field1", "field2");
    }

    private MappingJacksonValue getMappingJacksonValue(Object someModels, String ...fields) {
        SimpleBeanPropertyFilter simplePropFilter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filter = new SimpleFilterProvider().addFilter("SomeModelFilter2", simplePropFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someModels);
        mappingJacksonValue.setFilters(filter);
        return mappingJacksonValue;
    }
}
