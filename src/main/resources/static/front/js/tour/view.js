const items = [
    [126.94321920274982, 37.55772142387766],
    [126.94519221656536, 37.55648123246987],
    [126.94560997964005, 37.55783517406359]
];

window.addEventListener("DOMContentLoaded", function () {
    const mapEl = document.getElementById("map");
    mapEl.style.width = "1000px";
    mapEl.style.height = "600px";

    let map;

    navigator.geolocation.getCurrentPosition((pos) => {
        const {latitude, longitude} = pos.coords;

        const mapOption = {
            center: new kakao.maps.LatLng(latitude, longitude),
            level: 3,
        };

        map = new kakao.maps.Map(mapEl, mapOption);

        const markerPos = new kakao.maps.LatLng(latitude, longitude); // 좌표 객체
        const marker = new kakao.maps.Marker({ // 생성자
            position: markerPos // 매개변수
        });
        marker.setMap(map); // 마커 표기

        mapProcess(map);
    });

    // 지도 클릭시 좌표 정보
    /*
    if (map) {
        kakao.maps.event.addListener(map, 'click', function (e) {
            console.log(e);
        });

    } // endif
     */

    function mapProcess(map) {
        // 지도 클릭시 좌표 정보
        kakao.maps.event.addListener(map, 'click', function(e) {
            const latLng = e.latLng;
           const marker = new kakao.maps.Marker({
               position: latLng
           });

           marker.setMap(map);
        });
    }


});