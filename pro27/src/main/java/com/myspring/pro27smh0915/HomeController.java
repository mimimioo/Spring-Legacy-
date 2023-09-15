package com.myspring.pro27smh0915;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
  /**
  * Simply selects the home view to render by returning its name.
  */
  @RequestMapping(value = "/main", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    logger.info("Welcome home! The client locale is {}.", locale);

    // ?‚˜ì¤‘ì—, ?ŒŒ?¼ëª…ì—?„œ, ì¤‘ë³µ?ŒŒ?¼ ?”¼?•˜ê¸°ìœ„?„œ ?‚¬?š©?•˜?Š” ?„¤?´ë°? ê¸°ë²• ì¤‘í•˜?‚˜?¸?°.
    // 1) ?‹œê°? ë°? ?‚ ì§œë?? ?´?š©?•˜ê³?,
    // 2) UUID , ?Š¹? • ?œ?¤?•œ ?ˆ«? ë°? ?˜ë¬¸ìë¥? ?ƒ?„±?•´ì£¼ëŠ” ?„êµ¬ë?? ?´?š©?•˜ê¸°ë„?•¨.
    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, 
    DateFormat.LONG, locale);
    String formattedDate = dateFormat.format(date);
    // ?‹¨?ˆœ ?°?´?„°ë§?, ë·°ì— ? „?‹¬?•˜?Š” êµ¬ì¡°ë§? ? ?‹œ ë³´ë©´ ?¨. 
    model.addAttribute("serverTime", formattedDate );
    // ê²°ê³¼ ë·°ëŠ”, ëª¨ë¸&ë·? ?˜•?‹?´ ?•„?‹ˆ?¼, ?‹¨?ˆœ ë·? ë¦¬ì¡¸ë²? ?•´?‹¹ ë·°ë¡œ ?´?™?•¨. 
//    return "home";
    // ë·? ë¦¬ì¡¸ë²? ???‹ , ???¼ì¦ˆë?? ?´?š©?•´?„œ, ë©”ì¸?— ? ‘?†?•´ë³´ê¸°.
    return "main";
  }
}


//ï¿½Ù±ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿? ï¿½Úµï¿½
/*
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "main";
	}
}

*/