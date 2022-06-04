package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dao.StatsDao;
import vo.Stats;

@WebListener
public class CountListener implements HttpSessionListener {
	private StatsDao statsDao;
	public void sessionCreated(HttpSessionEvent se)  { 
    	// 현재 접속자 수 --> 톰켓안에 세션의 수
    	int currentCount = (Integer)(se.getSession().getServletContext().getAttribute("currentCount"));
    	se.getSession().getServletContext().setAttribute("currentCount", currentCount+1);
    	// 날짜별 접속자 수 --> DB를 이용
    	this.statsDao = new StatsDao();
    	Stats stats = statsDao.selectStatsOneByNow();
    	System.out.println(stats);
    	
    	if(stats == null) { // 오늘 날짜 카운트가 없다
    		statsDao.insertStats(); // 오늘 날짜로 카운트 1 추가
    	} else { // 오늘 날짜 카운트가 있다
    		statsDao.updacashbookatsByNow(); // 오늘 날짜 카운트를 1 증가 
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	int currentCount = (Integer)(se.getSession().getServletContext().getAttribute("currentCount"));
    	System.out.println("CountListenersessionDestroyedafter : " + currentCount);
    	se.getSession().getServletContext().setAttribute("currentCount", currentCount-1);
    }
	
}
