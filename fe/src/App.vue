<template>
  <div>
    <router-view />
  </div>
</template>

<style>
.background {
  display: flex;
  justify-content: center;
}

.body {
  width: 390px;
  height: 844px;
  background-color: #f2f2f7;
}

.footer {
  position: fixed;
  width: 100%;
  width: 390px;
  height: 73px;
  bottom: 0;
  background: white;
}

.title {
  text-align: center;
  font-size: 40px;
  /* background-color: red; */
}

/* 폰트 */
.v-application {
  font-family: "GmarketSansTTF", sans-serif !important;
}
@font-face {
  font-family: "GmarketSansTTFLight";
  src: url("./assets/fonts/GmarketSansTTFLight.ttf") format("truetype");
  /* font-weight: 400; */
}
@font-face {
  font-family: "GmarketSansTTFMedium";
  src: url("assets/fonts/GmarketSansTTFMedium.ttf") format("truetype");
  /* font-weight: 300; */
}

@font-face {
  font-family: "GmarketSansTTFBold";
  src: url("assets/fonts/GmarketSansTTFBold.ttf") format("truetype");
  /* font-weight: 300; */
}
</style>
