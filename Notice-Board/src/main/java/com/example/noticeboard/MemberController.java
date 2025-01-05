package com.example.noticeboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@Slf4j
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model) {
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referer);
        log.info("uri={}", referer);
        model.addAttribute("login", new LoginDto());
        return "login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("login") LoginDto loginDto, HttpServletRequest request, HttpSession session, Model model) {
        boolean login = memberService.login(loginDto);

        if (login) {
            String username = loginDto.getUsername();
            Member member = memberService.findByUserName(username);
            session.setAttribute("loginMember", member);

            String prevPage = (String) request.getSession().getAttribute("prevPage");
            request.getSession().removeAttribute("prevPage");

            return "redirect:" + (prevPage != null ? prevPage : "/");
        }

        model.addAttribute("error", "비밀번호 또는 아이디가 올바르지 않습니다.");
        return "login";
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginMember");
        return "redirect:/";
    }

    // 회원가입 페이지
    @GetMapping("/join")
    public String getJoinPage(Model model) {
        model.addAttribute("member", new MemberDto()); // 회원가입 폼에 사용할 객체 전달
        return "joinMember"; // 회원가입 템플릿 반환
    }

    // 회원가입 처리
    @PostMapping("/joinMember")
    public String createMember(@Valid @ModelAttribute("member") MemberDto memberDto,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "joinMember"; // 유효성 검증 실패 시 다시 회원가입 페이지로 반환
        }
        memberService.save(memberDto); // 서비스 계층에서 회원 저장 로직 호출
        return "redirect:/login"; // 성공 시 로그인 페이지로 리다이렉션
    }

    // 잘못된 GET 요청 처리
    @GetMapping("/createMember")
    public String handleCreateMemberGet() {
        return "redirect:/join"; // 회원가입 페이지로 리다이렉트
    }
}
