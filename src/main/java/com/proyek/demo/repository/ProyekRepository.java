package com.proyek.demo.repository;

import com.proyek.demo.entity.Proyek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyekRepository extends JpaRepository<Proyek, Long> {
}
