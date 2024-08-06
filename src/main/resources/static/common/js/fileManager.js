/**
 * 파일 업로드, 삭제, 조회 공통 기능
 */

const fileManager = {
    /**
     * 파일 업로드
     */
    upload(files, gid, location) {

    },
    /**
     * 파일 삭제
     */
    delete() {

    },
    /**
     * 파일 조회(검색)
     */
    search() {

    }

};

window.addEventListener("DOMContentLoaded", function (){
    // 파일 업로드 버튼 이벤트 처리 B
   const fileUploads = document.getElementsByClassName("fileUploads");
   const fileEl = document.createElement("input");
   fileEl.type = 'file';
   fileEl.multiple = true;

   for (const el of fileuploads) {
       el.addEventListener("click", function() {
           fileEl.value = ""; // 초기화 작업
           delete fileEl.gid;
           delete fileEl.location
           const dataset = this.dataset;
           fileEl.gid = dataset.gid;
           if (dataset.location) fileEl.location = dataset.location;

          fileEl.click(); // 동적 추가
       });
   }
   // 파일 업로드 버튼 이벤트 처리 D

    // 파일 업로드 처리
    fileEl.addEventListener("change", function (e) {
        const files = e.target.files; // 현재 발생한 요소에 파일 속성이 추가
       // 파일이 선택이 되어있지 않은 경우에 대한 예외 처리
        fileManager.upload(files, fileEl.gid, fileEl.location);
    });
});