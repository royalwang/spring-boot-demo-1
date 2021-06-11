package com.bambrow.spring.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class ComplexAssemblySpringTest {
    @Test
    public void complexAssemblyTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "complex_assembly_context.xml" }
        );
        ComplexAssembly ca1 = context.getBean("complexAssembly1", ComplexAssembly.class);
        ComplexAssembly ca2 = context.getBean("complexAssembly2", ComplexAssembly.class);
        assert(ca1.getId() == 1);
        assert(ca2.getId() == 2);

        List<String> list = Arrays.asList("list1", "list2", "list3");
        assert(ca1.getList().equals(list));
        assert(ca2.getList().equals(list));

        Map<String, String> map = new HashMap<>();
        map.put("map1", "value1");
        map.put("map2", "value2");
        map.put("map3", "value3");
        assert(ca1.getMap().equals(map));
        assert(ca2.getMap().equals(map));

        Properties props = new Properties();
        props.setProperty("prop1", "value1");
        props.setProperty("prop2", "value2");
        props.setProperty("prop3", "value3");
        assert(ca1.getProperties().equals(props));
        assert(ca2.getProperties().equals(props));

        Set<String> set = new HashSet<>();
        set.add("set1");
        set.add("set2");
        set.add("set3");
        assert(ca1.getSet().equals(set));
        assert(ca2.getSet().equals(set));

        String[] arr = new String[] { "array1", "array2", "array3"};
        assert(Arrays.equals(ca1.getArray(), arr));
        assert(Arrays.equals(ca2.getArray(), arr));
    }
}
