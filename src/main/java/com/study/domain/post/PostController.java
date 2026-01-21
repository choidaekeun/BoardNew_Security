package com.study.domain.post;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.common.file.FileUtils;
import com.study.common.paging.PagingResponse;
import com.study.domain.file.FileRequest;
import com.study.domain.file.FileResponse;
import com.study.domain.file.FileService;
import com.study.domain.member.MemberResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	private final FileService fileService;
    private final FileUtils fileUtils;
	
	@GetMapping("/post/write")
	public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
		if (id !=null) {
			PostResponse post = postService.findPostById(id);
			model.addAttribute("post", post);
		}

		return "/user/post/write";
		
	}
	
	// 신규 게시글 생성
    @PostMapping("/post/save")
    public String savepost(final PostRequest params, Model model) {
    	Long id = postService.savePost(params); // 1. 게시글 INSERT
    	List<FileRequest> files =  fileUtils.uploadFiles(params.getFiles()); // 2. 디스크에 파일 업로드
    	fileService.saveFiles(id, files); // 3. 업로드 된 파일 정보를 DB에 저장
    	MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/user/post/list", RequestMethod.GET, null);
    	return showMessageAndRedirect(message, model);
    }
    
    // 게시글 리스트 페이지
    @GetMapping("/post/list")
    public String openPostList(@ModelAttribute("params") final SearchDto params, Model model) {
    	PagingResponse<PostResponse> response = postService.findAllPost(params);
    	model.addAttribute("response", response);
    	return "/user/post/list";
    }
    
    // 게시글 상세 페이지
    @GetMapping("/post/view")
    public String openPostview(@RequestParam(value = "id") final Long id, Model model) {
    	PostResponse post = postService.findPostById(id);
    	postService.viewCount(id);
    	
    	model.addAttribute("post", post);
    	
//    	System.out.println("####post=>" + post.getTitle());
    	return "/user/post/view";
    }
    
    
 // 게시글 상세 페이지
//    @GetMapping("/post/view.do/{id}")
//    public String openPostview(@PathVariable(value = "id") final Long id, Model model) {
//    	PostResponse post = postService.findPostById(id);
//    	postService.viewCount(id);
//    	
//    	model.addAttribute("post", post);
//    	
////    	System.out.println("####post=>" + post.getTitle());
//    	return "post/view";
//    }
//    
    
    // 기존 게시글 수정
    @PostMapping("/post/update")
    public String updatePost(final PostRequest params, final SearchDto queryParams, Model model) {
    	
    	// 1. 게시글 정보 수정
        postService.updatePost(params);

        // 2. 파일 업로드 (to disk)
        List<FileRequest> uploadFiles = fileUtils.uploadFiles(params.getFiles());

        // 3. 파일 정보 저장 (to database)
        fileService.saveFiles(params.getId(), uploadFiles);

        // 4. 삭제할 파일 정보 조회 (from database)
        List<FileResponse> deleteFiles = fileService.findAllFileByIds(params.getRemoveFileIds());

        // 5. 파일 삭제 (from disk)
        fileUtils.deleteFiles(deleteFiles);

        // 6. 파일 삭제 (from database)
        fileService.deleteAllFileByIds(params.getRemoveFileIds());
    	
    	MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/user/post/list", RequestMethod.GET, queryParamsToMap(queryParams));
    	return showMessageAndRedirect(message, model);
    }
    
    // 게시글 삭제
    @PostMapping("/post/delete")
    public String deletePost(@RequestParam(value = "id") final Long id, final SearchDto queryParams, Model model) {
    	postService.deletePost(id);
    	MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/user/post/list", RequestMethod.GET, queryParamsToMap(queryParams));
    	return showMessageAndRedirect(message, model);
    }
    
    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
    	model.addAttribute("params", params);
        return "/common/messageRedirect";
    }
    
    // 쿼리 스트링 파라미터를 Map에 담아 반환
    private Map<String, Object> queryParamsToMap(final SearchDto queryParams) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", queryParams.getPage());
        data.put("recordSize", queryParams.getRecordSize());
        data.put("pageSize", queryParams.getPageSize());
        data.put("keyword", queryParams.getKeyword());
        data.put("searchType", queryParams.getSearchType());
        return data;
    }
    
    // 비밀글 체크
    @PostMapping("/secretCheck")
    @ResponseBody
    public Boolean secretPost(HttpServletRequest request) {
    	
    	// 1. 회원 정보 조회
    	Long Id = Long.parseLong(request.getParameter("Id"));
    	String password = request.getParameter("password");
    	Boolean result = postService.secret(Id, password);
    	
    	return result;
    	
    }

}
