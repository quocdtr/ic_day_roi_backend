package com.example.demo.sercurity;

public class Endpoints {
    public static final String front_end_host = "http://localhost:3000";
    public static final String[] PUBLIC_GET_ENDPOINT = {
            "/san-pham",
            "/san-pham/**",
            "/hinh-anh",
            "/nguoi-dung/search/existsByTenDangNhap",
            "/nguoi-dung/search/existsByEmail",
            "/tai-khoan/kich-hoat",
    };
    public static final String[] PUBLIC_POST_ENDPOINT = {
            "/tai-khoan/dang-ky",
            "/tai-khoan/dang-nhap",
    };
    public static final String[] ADMIN_GET_ENDPOINT = {
            "/nguoi-dung",
            "/nguoi-dung/**",
    };
    public static final String[] ADMIN_POST_ENDPOINT = {
            "/san-pham",

    };
}
