const mapLib = {
    /**
     * 지도 로드
     *
     * @param mapId : 지도를 출력할 요소 Id 이름
     * @param width : 지도 너비
     * @param height : 지도 높이
     * @param options - 옵션
     *                - center: {lat: 위도, lng: 경도, ... }  - 필수 항목
     *                - zoom: 확대 정도(1~10) / 숫자가 작을 수록 확대
     *                - markerImage : 공통 마커 이미지 주소값, 개별 마커 이미지가 있는 경우는 그걸로 대체,
     *                - marker : [{lat: 위도, lng: 경도, info: html 데이터(인포윈도우 출력값), images: 이미지 경로 주소값 - 마커 이미지}, ... ] (배열 형태)
     *
     */
    load(mapId, width = 300, height = 300, options) { // 너비x높이 300px 기본값 지정
        const mapEl = document.getElementById(mapId);
        if (!mapEl || !options?.center ) return; // center 필수항목으로 설정
        mapEl.style.width = `${width}px`;
        mapEl.style.height = `${height}px`;

        let {center, marker, markerImage } = options; // options이라는 객체의 구조에서 center , marker, markerImage의 값를 가져와서 center, marker, markerImage  변수에 할당 => 비구조화 할당 구조화되어있던 부분들을 분해해서 담아냄
        // 실제 구조는 view2.js const options 객체의 항목들

        // 지도 가운데 좌표 처리 B

        const zoom = options?.zoom ?? 3; // 기본값 3
            const position = new kakao.maps.LatLng(center.lat, center.lng);
            const map = new kakao.maps.Map(mapEl, {
                center: position,
                level: zoom,
            });

        // 지도 가운데 좌표 처리 D

        // 마커 출력 처리 B
        if (marker) {

            if (!Array.isArray(marker)) marker = [marker]; // 배열 객체 아닐 때 즉, 일반 객체 일때

            const markers = marker.map(m => {

                const opt = { position: new kakao.maps.LatLng(m.lat, m.lng)};

                const mi = markerImage ? markerImage : m?.image; // marker 이미지 처리
                if (mi) {
                    const mImage = new kakao.maps.MarkerImage(mi, new kakao.maps.Size(64, 69), {offset: new kakao.maps.Point(27, 69)});
                    opt.image = mImage;
                }

                const _marker = new kakao.maps.Marker(opt);

                _marker.setMap(map);

                return _marker;
            });

        } // endif
        // 마커 출력 처리 D

    }

};