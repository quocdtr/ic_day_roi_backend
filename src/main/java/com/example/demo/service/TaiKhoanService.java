package com.example.demo.service;

import com.example.demo.dao.NguoiDungRepository;
import com.example.demo.entity.NguoiDung;
import com.example.demo.entity.ThongBao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaiKhoanService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> dangKyNguoiDung(NguoiDung nguoiDung) {


        if (nguoiDungRepository.existsByTenDangNhap(nguoiDung.getTenDangNhap())) {
            return ResponseEntity.badRequest().body(new ThongBao("Tên đăng nhập đã tồn tại"));
        }
        if (nguoiDungRepository.existsByEmail(nguoiDung.getEmail())) {
            return ResponseEntity.badRequest().body(new ThongBao("Email đã tồn tại"));
        }
        // Mã hóa mật khẩu
        String encryptPassword = passwordEncoder.encode(nguoiDung.getMatKhau());
        nguoiDung.setMatKhau((encryptPassword));

        //gán và gửi thông tin mã kích hoạt
        nguoiDung.setMaKichHoat(taoMaKichHoat());
        nguoiDung.setDaKichHoat(false);

        NguoiDung nguoiDung_daDangKy = nguoiDungRepository.save(nguoiDung);

        //Gửi Email cho người dùng để họ kích hoạt
        guiEmailKichHoat(nguoiDung.getEmail(), nguoiDung.getMaKichHoat());
        return ResponseEntity.ok("Đăng ký thành công");
    }
    private String taoMaKichHoat(){
        // Tạo mã ngaaux nhiên
        return UUID.randomUUID().toString();
    }

    private void guiEmailKichHoat(String email, String maKichHoat){
        String subject = "Kích hoạt tài khoản của bạn tại IC day Roi";
        String text = "Vui lòng sử dụng mã sau để kích hoạt cho tài khoản của bạn <"+email+">:<html><body><br/><h1"+maKichHoat+"</h1></html></body>";
        text+="<br/> Click vào đường link để kích hoạt tài khoản: ";
        String url ="http://localhost:3000/kich-hoat/"+email+"/"+maKichHoat;
        text+="<br/> Click vào đường link để kích hoạt tài khoản: ";
        text+=("<br/> <a href="+url+">"+url+"</a> ");
        emailService.sendMessage("nguyenbaquoct2002@gmail.com", email, subject, text);
    }


    public ResponseEntity<?> kichHoatTaiKhoan(String email, String maKichHoat) {
        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(email);
        if(nguoiDung==null){
            return ResponseEntity.badRequest().body(new ThongBao("Người dùng không tồn tại"));
        }
        if(maKichHoat.equals(nguoiDung.getMaKichHoat())){
            nguoiDung.setDaKichHoat(true);
            nguoiDungRepository.save(nguoiDung);
            return ResponseEntity.ok("Kích hoạt tài khoản thành công");
        }else{
            return ResponseEntity.badRequest().body(new ThongBao("Mã kích hoạt không chính xác"));
        }
    }

}
