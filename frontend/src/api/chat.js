import { apiInstanceWithoutAuthorization } from "./index.js";

const api = apiInstanceWithoutAuthorization();

function getHotspots(param, success, fail) {
  api.get(`/data/hotspot`).then(success).catch(fail);
}

function getBestMenus(districtId, success, fail) {
  api
    .get(`/data/menu`, { params: { districtId: districtId } })
    .then(success)
    .catch(fail);
}

function getTodayWeather(districtId, success, fail) {
  api
    .get(`/data/weather`, { params: { districtId: districtId } })
    .then(success)
    .catch(fail);
}

export { getHotspots, getBestMenus, getTodayWeather };
