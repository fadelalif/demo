package com.proyek.demo.controller;

import com.proyek.demo.entity.Proyek;
import com.proyek.demo.service.ProyekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proyek")
public class ProyekController {

    @Autowired
    private ProyekService proyekService;

    @GetMapping
    public List<Proyek> getAllProyek() {
        return proyekService.getAllProyek();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyek> getProyekById(@PathVariable Long id) {
        Optional<Proyek> proyek = proyekService.getProyekById(id);
        return proyek.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Proyek createProyek(@RequestBody Proyek proyek) {
        return proyekService.saveProyek(proyek);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyek> updateProyek(@PathVariable Long id, @RequestBody Proyek proyekDetails) {
        Optional<Proyek> proyek = proyekService.getProyekById(id);
        if (proyek.isPresent()) {
            Proyek proyekToUpdate = proyek.get();
            proyekToUpdate.setNamaProyek(proyekDetails.getNamaProyek());
            proyekToUpdate.setClient(proyekDetails.getClient());
            proyekToUpdate.setTglMulai(proyekDetails.getTglMulai());
            proyekToUpdate.setTglSelesai(proyekDetails.getTglSelesai());
            proyekToUpdate.setPimpinanProyek(proyekDetails.getPimpinanProyek());
            proyekToUpdate.setKeterangan(proyekDetails.getKeterangan());
            proyekService.saveProyek(proyekToUpdate);
            return ResponseEntity.ok(proyekToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyek(@PathVariable Long id) {
        proyekService.deleteProyek(id);
        return ResponseEntity.noContent().build();
    }
}
