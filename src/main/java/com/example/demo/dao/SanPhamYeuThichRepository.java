package com.example.demo.dao;

import com.example.demo.entity.SanPhamYeuThich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "san-pham-yeu-thich")
public interface SanPhamYeuThichRepository extends JpaRepository<SanPhamYeuThich, Integer> {
}
