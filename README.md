■ Sola Delivery [배달 프로그램]

 ◆ 완료
 - 로그인 기능 구현
 - 각 항목에 필요한 포맷 작성 완료
 - 게시판 기능 구현
 - 댓글 및 대댓글 기능 구현
 - 로그 기능 구현


 ◆ 진행중
 - 주문 처리 시스템 구현중
 - 통합게시판 구현중


 ◆ 미완료
 - 고객센터 기능 구현
 - 관리자 기능 추가
 - 다중로그인 기능 구현
 - 대댓글 수정 및 삭제 기능 구현
 - 검색 기능 구현
 - 재주문율 알고리즘 구현
 - 이벤트 구현
 - 쿠폰 시스템 구현
 - 정산 시스템 구현


 ■ 설계도
    - 선택 메뉴 정렬 알고리즘
    ```
    Temp - menu, price, explain;
    List<Temp> temp;
        sum = 0;
        count = 0;
    while(true) {
        int choice = Prompt.inputInt("메뉴 선택 : ");
        if(choice == -1) {
            break;
        }
        
        Iterator<Menu> list = menuList.iterator();

        while(list.hasNext()) {
            Menu m = list.next();
            if(CategoryNumber == m.getCategoryId() && ResNumber == m.getId()) {
                if(choice == m.getNumber()) {
                    Temp t = new Temp();
                    t.setMenu(m.getName());
                    t.setPrice(m.getPrice());
                    t.setExplain(m.getExplain());
                    temp.add(t);
                    sum += m.getPrice();
                    count++;
                }
            }
        }
    }

    sort();

    // 가게 메뉴 개수 카운트 후,
    // 카운트 수 만큼, 임시 리스트 생성

    private void sort() {
        Iterator<Temp> temp = tempList.iterator();
        
        while(temp.hasNext()) {
            Temp t = temp.next();
            
            if(메뉴.equals(t.getName())) {
                first++;
                firstSum += t.getPrice();
            }
            
            if(t.getName.equal())

        }
    }
    ```
    