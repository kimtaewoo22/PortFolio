# PortFolio

안녕하세요 갓 국비학원을 졸업한 남자 포트폴리오 입니다.

제가 진행한 프로젝트의 내용과 맡은 역할 위주로 설명을 드리며, 프로젝트 진행시 어려웠던 부분에 대해서만 설명드리겠습니다.

일단 제가 맡은 역할은 회원 관리 시스템 및 공지사항 게시판 입니다.

프로젝트 주제 : 관심사에 따라 취미를 추천해주는 취미 큐레이팅 서비스 입니다.

서류를 제외한 프로젝트 실 개발기간 : 2020.01.28 - 2020.03.07
총 개발인원 : 5명

시스템 개요 : 커뮤니티를 통해 같은 취미를 가진 사람들끼리 소통하며 , 크리에이터가 제공한
취미 강의 영상을 구매해 수강할 수 있습니다. 


기술적 고려사항 : 

■ Web Application Architecture  
  – 2Layer / MVC 
■ Front End  
  – HTML5 / CSS3 / Bootstrap / javascript / jQuery / Ajax / FulCalendar / summernote/ Font Awesome / Google Font / sweet Alert  
■ Back End 
  – Java / Spring Framework / Mybatis / Apache / ApacheTomcat 
■ DB / Tool  
  – oracle / starUML / OvenApp.io / eclipse / GitHub / DBeaver  
■ Open API  
  –SNS로그인 : 네이버 / 카카오
  –Naver Captcha
  –SMS(문자서비스) : 청기와랩  
  –Email : javaMail  
  –주소 : 다음 주소 
  –결제 : 아임포트  
  
업무 영역 :
 ■ 회원 관리 시스템
 ■ 공지사항 게시판
 
 # 회원 관리 시스템
 1. 비회원, 일반회원, 크리에이터 회원, 관리자로 구분이 됩니다.
 2. 회원가입 진행시 회원아이디는 Ajax로 중복체크가 가능합니다.
 3. 회원가입 단계에서 각 입력창에 이벤트를 주어 validation체크 하였습니다.
 4. 로그인 진행시 3회 이상 틀릴경우 Captcha로 인증 해야 가능합니다.
 5. 비밀번호 분실시 javamail로 이메일 인증 후 변경 가능합니다.
 6. 일반회원 > 크리에이터 회원 변경 시 청기와랩으로 인증 해야 합니다.
 7. 관리자는 게시판 형식으로 회원관리를 할 수 있습니다.
 8. 정렬기능, 검색기능 , 회원탈퇴시키기, 정지시키기가 가능합니다.
 # 공지사항 게시판
 1. 공지사항 글쓰기는 summernote API를 사용 했습니다
 2. 공지사항은 게시판 형식으로 보여집니다.
 3. 공지사항 CRUD가 가능합니다.
 
  # 프로젝트 진행시 어려웠던 부분과 극복한 사례
  1. Ajax를 통해 비동기로 Data를 처리를 알게 되었고, Ajax에 대해 이해할 수 있게 되었습니다.
  2. API의 사용법을 이해거나 적용하기가 어려웠는데 , 
     Http를 이용해 ID/Secret 값을 넣어서 보내고 JSON Data를 받고 처리되는 방식에 대해 알게 되었습니다
  3. 아무래도 개발은 협업이기 때문에 진행시 의견충돌과 여러의견이 많았는데 그것을 조율하고 결정하는 부분에 있어서 어려웠습니다.
     그런 조율 하는 부분에서 
