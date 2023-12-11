package com.example.demo.dao;

import com.example.demo.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "san-pham")
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    Page<SanPham> findByTenSanPhamContaining(@RequestParam("tenSanPham") String tenSanPham, Pageable pageable);
    Page<SanPham> findByDanhSachTheLoai_MaTheLoai(@RequestParam("maTheLoai") int maTheLoai, Pageable pageable);
    Page<SanPham> findByTenSanPhamContainingAndDanhSachTheLoai_MaTheLoai(@RequestParam("tenSanPham") String tenSanPham, @RequestParam("maTheLoai") int maTheLoai, Pageable pageable);
}
