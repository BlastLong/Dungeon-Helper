# 던전 도우미(Dungone Helper)
마인크래프트 서버 '마인플래닛' 전용 클라이언트 사이드 모드

![Dungeon-Helper](/images/Overview.png)

## 기술 스택
![Java](https://img.shields.io/badge/Java%2017-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white)
![Forge](https://img.shields.io/badge/Forge-E04E14?style=flat-square&logo=minecraft&logoColor=white)
![Fabric](https://img.shields.io/badge/Fabric-DBD0B4?style=flat-square&logo=minecraft&logoColor=black)


## 기능 설명
- 각 직업별 스킬 쿨타임 표시
    - 서버에는 여러 직업들이 있고 각 직업에 맞는 스킬들을 가지고 있음
    - 스킬을 쓰면 마나가 줄며 스킬이 나가지만, 쿨타임이 존재하여 난사하는 것이 불가함
    - 여러 스킬들이 있고 각 스킬마다 쿨타임이 있지만, 텍스트로 밖에 보여주지 않아 게임 플레이하는 중에 확인하는 것이 어려움
    - 이를 해결하기 위해 화면 중앙 하단에 각 직업별로 스킬들의 쿨타임을 표시하여 실시간으로 쿨타임을 체크하며 스킬을 사용할 수 있게 만듦
- 던전 쿨타임 표시
    - 서버에는 총 8개의 던전이 있고 각 던전에 입장하면 1시간 동안 재입장이 불가함
    - 1시간동안 8 던전을 돌고 기다리는 경우가 많은데, 확인하고자 할 때 명령어로 ‘/쿨타임’을 입력해야하는 번거로움이 존재함
    - 이를 완화하기 위해서 자체 카운터를 이용해 실시간으로 던전 쿨타임을 확인하고 재입장을 할 수 있도록 함
- 커스텀 인첸트 등급별 별도의 텍스쳐 사용
    - 캐릭터 장비에 적용가능한 스킬이 있는데, 이 스킬은 책으로 되어있음
    - 이 책을 커스텀 인첸트라고 하는데, 이 책에도 등급이 나뉘며 이에 따라 희귀도도 다름
    - 하지만 서버에서는 등급 상관없이 책이 똑같이 보여서 거래소에서 원하는 책을 찾을 때 힘듦
        - 기능 추가할 때의 텍스쳐는 등급 상관없이 같았음
        - 현재는 서버 업데이트로 등급에 따라 다르지만, 눈에 잘 들어오지 않음
    - 이를 완화하고자 각 등급마다 다른 색의 책 텍스쳐를 입혀서 구분할 수 있게 함
- 설정
    - 앞서 설명한 스킬 쿨타임, 던전 쿨타임, 커인 텍스쳐에서 사용하는 자잘한 값이나 옵션을 바꿀 수 있는 설정을 추가함
    - 스킬 쿨타임에서는 스킬 쿨타임 표시여부, 표시할 스킬의 직업 / 던전 쿨타임에서는 던전 쿨타임 표시여부, 페이드 효과 온오프 / 커인 텍스쳐에서는 텍스쳐 표시 여부를 설정할 수 있음

## 설치 및 실행방법

### Forge
1. 다음 링크에서 Forge 1.20.1 Recommended 인스톨러 다운로드  
[Forge 사이트](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html)

2. 인스톨러를 실행해서 설치

3. %appdata%/.minecraft에 Mods 폴더를 생성

4. 던전 도우미 모드를 폴더에 넣고 마인크래프트 실행


### Fabric
1. 다음 링크에서 Fabric 인스톨러 다운로드  
[Fabric 사이트](https://fabricmc.net/use/installer/)

2. 인스톨러를 실행하고 1.20.1, 로더버전 0.14.22로 설정하고 설치

3. 다음 링크에서 Fabric API 1.20.1 최신버전 다운로드  
[Fabric API 다운로드 사이트](https://www.curseforge.com/minecraft/mc-mods/fabric-api/files/all?page=1&pageSize=20)

3. %appdata%/.minecraft에 Mods 폴더를 생성

4. 던전 도우미 모드, Fabric API 1.20.1 두개를 폴더에 넣고 마인크래프트 실행