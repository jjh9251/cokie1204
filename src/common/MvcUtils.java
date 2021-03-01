package common;

public class MvcUtils {

	public static String getPageBar(int totalContents, int cPage, int numPerPage, String url) {
		
		String pageBar = "";
		
		int pageBarSize = 5;
		int totalPage = (int)Math.ceil((double)totalContents / numPerPage);

		// /mvc/admin/memberList?cpage=
		// /mvc/admin/memberFinder?searchType=memberId&searchKeyward=a&cpage=
		url = url + (url.indexOf("?") > -1 ? "&" : "?") + "cPage=";
		
		// 1 2 3 4 5 : pageStart 1 ~ pageEnd 5 
		// 6 7 8 9 10 : pageStart 6 ~ pageEnd 10 
		int pageStart = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;
		
		//증감변수
		int pageNo = pageStart;
		
		//이전 영역
		if(pageNo == 1) {
			
		}
		else {
			// /mvc/admin/memberList?cpage=5
			pageBar += "<a href='" + url + (pageNo - 1) + "'>&lt;</a>\n";
		}
		
		//페이지 No 영역
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			//현재페이지인 경우, 링크비활성화
			if(pageNo == cPage) {
				pageBar += "<span class='cPage'>" + pageNo + "</span>\n";
			}
			else {
				pageBar += "<a href='" + url + pageNo + "'>" + pageNo + "</a>\n";
			}
			pageNo++;
		}
		//다음 영역
		if(pageNo > totalPage) {
			
		}
		else {
			pageBar += "<a href='" + url + pageNo + "'>&gt;</a>\n";
		}
		return pageBar;
	}

}
