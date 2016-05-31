package com.sist.hadoop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.mapred.MovieDriver;
import com.sist.movie.MovieDTO;
import com.sist.movie.MovieDataManager;

/*
 *    Java
 *       = 객체지향 (객체:추상화(모델링))
 *           1) 새로운 기능 추가 VS 수정 
 *           2) 재사용 (상속 VS 포함)
 *           3) 추상클래스 VS 인터페이스 
 *           4) 예외처리 
 *    Oracle
 *        = 프로시저 VS 트리거 VS 함수 
 *      = JOIN VS SUBQUERY , AOP
 *        = 제약조건 
 *    Jsp
 *       = MVC (GET , POST)
 *    Spring               ======> XML VS Annotation
 *        = DI , AOP , Container
 *    Hadoop
 *          = MapReduce , 설치
 *    R
 */
@Controller
public class MainController {
	 @Autowired
     private MovieDataManager mgr;
	 @Autowired
	 private MovieDriver md;
	 @RequestMapping("movie/list.do")
	 public String movie_list(Model model)
	 {
		 //   "/movie/list.jsp"
		 List<MovieDTO> list=mgr.movieAllData();
		 List<String> raList=mgr.movie_rank();
		 List<String> reList=mgr.movie_reserve();
		 List<String> bList=mgr.movie_boxoffice();
		 model.addAttribute("list", list);
		 model.addAttribute("raList", raList);
		 model.addAttribute("reList", reList);
		 model.addAttribute("bList", bList);
		 return "movie/list";
	 }
	 @RequestMapping("movie/detail.do")
	 public String movie_detail(int no,Model model)
	 throws Exception
	 {
		 MovieDTO vo=mgr.movieDetail(no);
		 File file=new File("/home/sist/bigdataStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringWebHadoopProject/desc.txt");
		 if(file.exists())
		 {
			 file.delete();
		 }
		 for(int i=1;i<=3;i++)
		 {
			 String json=mgr.movie_review(vo.getTitle(), 1);
			 mgr.json_parse(json);
		 }
		 md.jobCall();
		 model.addAttribute("vo", vo);
		 return "movie/detail";
	 }
	 
	 @RequestMapping("movie/total.do")
	 public String movie_total(Model model)
	 {
		 List<MovieDTO> list=mgr.movieAllData();
		 String value="[";
		 int i=0;
		 for(MovieDTO d:list)
		 {
			 if(i==2)
			 {
			    value+="{"
                    +"name: '"+d.getTitle()+"',"
                    +"y: "+d.getReserve()+","
                    +"sliced: true,"
                    +"selected: true"
                         +"},";
			 }
			 else
			 {
				 value+="['"+d.getTitle()+"',"+d.getReserve()+"],";
			 }
			  i++;		 
		 }
		 
		 value=value.substring(0,value.lastIndexOf(','));
		 value+="]";
		 model.addAttribute("value", value);
		 model.addAttribute("list", list);
		 return "movie/total";
	 }
}










