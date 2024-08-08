window.addEventListener("DOMContentLoaded", function (){

    const html = "<h1>윈포윈도우 테스트</h1>";

    const options = {
       center: {
           lat: 37.55772142387766,
           lng: 126.94321920274982,
       },
       marker: [
           {lat:37.55772142387766, lng:126.94321920274982, info: { content: html, clickable: false, removable: true  }},
           {lat:37.55648123246987, lng:126.94519221656536, info: { content: html, clickable: true, removable: false  }},
           {lat:37.55783517406359, lng:126.94560997964005},
       ],
       markerImage: "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png",
   };
    mapLib.loadByCategory("AT4", 0, "map1", 1000, 600, options);

//    mapLib.loadByAddress("서울특별시 마포구 신촌로 176", 1, "map1", 1000, 600, options);
    //mapLib.loadByKeyword("제주도 맛집",1, "map1", 1000, 600, options);
    //mapLib.loadCurrentLocation("map1", 1000, 600, options);

  // mapLib.load("map1", 1000, 600, options);

    /*
   mapLib.load("map1", 300, 300, options);
       mapLib.load("map2", 400, 400, options);
       mapLib.load("map3", 500, 500, options);

     */
});