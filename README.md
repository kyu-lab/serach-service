# 검색 서비스
검색 서비스를 담당합니다.  
webflux + elasticsearch를 기반으로 작동합니다.  
사용자 또는 게시글 등 새로운 리소스가 생성시 카프카를 통해 데이터를 전달받습니다.  
전달받은 데이터는 elasticsearch에 저장되고 elasticsearch로 검색이 됩니다.

## 실행 환경
- openJdk 17
- elasticsearch
- webFlux
- kafka

# 설정 파일
## application-prod
운영환경에서 사용되는 설정 파일

## application-dev
로컬환경에서 사용되는 설정 파일로 커밋 금지
