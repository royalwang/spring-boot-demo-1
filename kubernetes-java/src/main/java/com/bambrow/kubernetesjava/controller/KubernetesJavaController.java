package com.bambrow.kubernetesjava.controller;

import com.bambrow.kubernetesjava.service.KubernetesJavaService;
import io.kubernetes.client.extended.kubectl.exception.KubectlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class KubernetesJavaController {

    @Autowired
    private KubernetesJavaService service;

    @GetMapping({"/ns", "/namespaces"})
    public List<String> listNamespaces() throws KubectlException {
        return service.listNamespaces();
    }

    @GetMapping("/nodes")
    public List<String> listNodes() throws KubectlException {
        return service.listNodes();
    }

    @GetMapping("/{namespace}/{kind}")
    public List<String> listObj(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "kind") String kind) throws KubectlException {
        return service.listObj(namespace, kind);
    }

    @GetMapping("/label/{namespace}/{kind}/{name}")
    public Map<String, String> labelObj(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "kind") String kind, @PathVariable(value = "name") String name) throws KubectlException {
        return service.labelObj(namespace, kind, name);
    }

}
