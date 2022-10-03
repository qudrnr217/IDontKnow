<template>
  <div class="background">
    <div class="body">
      <div class="content-box3">
        <div class="content-back">
          <div class="content-weather">
            <div class="weather-icon">
              <img src="@/assets/icon/main/temp.png" alt class="temp_icon" />
            </div>
            <div class="weather-data">16도</div>
            <div class="weather-icon">
              <img src="@/assets/icon/main/wind.png" alt class="wind_icon" />
            </div>
            <div class="weather-data">2m/s</div>
          </div>
          <div class="content-weather">
            <div class="weather-icon">
              <img src="@/assets/icon/main/humid.png" alt class="humid_icon" />
            </div>
            <div class="weather-data">36%</div>
            <div class="weather-icon">
              <img src="@/assets/icon/main/rain.png" alt class="rain-icon" />
            </div>
            <div class="weather-data">20%</div>
          </div>
          <div class="separator"></div>
          <div class="content-weather" style="height: 100%">
            햇볕에 노출 시 1-2시간 이내에도 피부 화상을 입을 수 있어 위험해요.
            한낮에는 실내나 그늘에 머물러주세요.
          </div>
        </div>
      </div>
      <footer-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "WeatherView",
  components: {},
};
</script>

<style scoped>
.current-location {
  display: flex;
  justify-content: flex-end;
  margin-right: 30px;
  margin-bottom: 10px;
}
.content-box3 {
  display: flex;
  justify-content: center;
  align-content: center;
  width: 100%;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
}
.content-back {
  display: flex;
  flex-direction: column;
  padding: 15px;
  background-color: #ffffff;
  border: none;
  border-radius: 10px;
  font-family: "GmarketSansTTFMedium";
  font-style: normal;
}
.content {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ffffff;
  width: 250px;
  height: 30px;
  border: none;
  border-radius: 10px;
  margin: 10px;
  padding: 10px;
  font-family: "GmarketSansTTFLight";
}
.content-weather {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: transparent;
  width: 200px;
  height: 30px;
  border: none;
  border-radius: 10px;
  margin: 10px;
  padding: 10px;
  font-family: "GmarketSansTTFLight";
}
.content-title {
  display: center;
  justify-content: center;
  flex-grow: 2;
  font-size: 15px;
  font-family: "GmarketSansTTFMedium";
  vertical-align: center;
}
.content_dropdown {
  flex-grow: 2;
  justify-content: center;
}
.weather-icon {
  width: 20px;
  flex-grow: 3;
  display: flex;
  justify-content: center;
}
.weather-title {
  flex-grow: 2;
  display: flex;
  justify-content: center;
}
.weather-data {
  flex-grow: 2;
  display: flex;
  justify-content: center;
}
.separator {
  border-top: 2px solid #ededed;
}
</style>
