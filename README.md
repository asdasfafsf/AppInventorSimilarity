# AppInventorSimilarity

# 많은 기술이 들어간 소스코드는 아니지만 순수 자바로 구현되었으며 버전은 Java 8입니다.
2017 한국정보처리학회 추계학술발표대회에 수록된 내용의 구현 코드입니다.

네이밍, 분할 등 기본적인 컨벤션이 지켜지지 않은 소스입니다. 후에 수정할 예정입니다.

앱 인벤터 유사도 비교 도구입니다. 현재 화면 단위로 유사도 측정이 가능하며 전체 앱에 대한 비교는 불가능합니다.

1.앱 인벤터로 만든 앱의 aia 파일을 추출

2.확장자를 zip(압축을 풀 수 있는 형태로 변경)

3.폴더 내부에 .bky 확장자를 가진 파일이 비교 대상이 됩니다.

4.프로그램 실행 후 bky 파일을 추가하고 해당 파일에 대한 이름 설정 및 k값 설정 후 유사도 버튼을 클릭하면 모든 경우의 수에 대한 유사도가 산출됩니다.



후에 개선 사항


1.aia 파일의 확장자 변경이 필요 없이 aia 파일 그 자체로 유사도가 비교가능 하도록 수정.


2.소스코드 리팩토링.
