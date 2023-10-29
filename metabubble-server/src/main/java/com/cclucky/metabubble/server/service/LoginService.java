package com.cclucky.metabubble.server.service;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.pojo.dto.LoginDTO;

import java.util.Map;

public interface LoginService {
    Result<Map<String, String>> login(LoginDTO loginDTO);
}
