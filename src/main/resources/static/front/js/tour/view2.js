window.addEventListener("DOMContentLoaded", function (){
   const options = {
       center: {
           lat: 37.55772142387766,
           lng: 126.94321920274982,
       },
       marker: [
           {lat:37.55772142387766, lng:126.94321920274982},
           {lat:37.55648123246987, lng:126.94519221656536},
           {lat:37.55783517406359, lng:126.94560997964005},
       ],
       markerImage: "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png",
   };

   mapLib.load("map1", 1000, 600, options);

    /*
   mapLib.load("map1", 300, 300, options);
       mapLib.load("map2", 400, 400, options);
       mapLib.load("map3", 500, 500, options);

     */
});