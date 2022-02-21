package com.bambrow.fabric8.controller;

import com.bambrow.fabric8.service.Fabric8Service;
import io.fabric8.kubernetes.client.Watch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Fabric8Controller {

    @Autowired
    private Fabric8Service service;

    @GetMapping({ "/ns", "/namespaces"})
    public List<String> listNamespaces() {
        return service.listNamespaces();
    }

    @GetMapping("/nodes")
    public List<String> listNodes() {
        return service.listNodes();
    }

    @GetMapping("/{namespace}/{kind}")
    public List<String> listObj(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "kind") String kind) {
        return service.listObj(namespace, kind);
    }

    @PostMapping("/ns/edit/{namespace}")
    public Map<String, String> editNamespace(@PathVariable(value = "namespace") String namespace) {
        return service.editNamespace(namespace).getMetadata().getLabels();
    }

    @PostMapping("/ns/create/{namespace}")
    public Map<String, String> createNamespace(@PathVariable(value = "namespace") String namespace) {
        return service.createNamespace(namespace).getMetadata().getLabels();
    }

    @GetMapping("/watch")
    public void createWatcher(@RequestParam("ms") Long ms) throws InterruptedException {
        Watch watcher = service.createWatcher();
        Thread.sleep(ms);
        watcher.close();
    }

}
