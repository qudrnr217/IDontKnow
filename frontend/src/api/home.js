import { apiInstanceWithoutAuthorization } from "./index.js";

const api = apiInstanceWithoutAuthorization();

function getHotspots(param, success, fail) {
  api.get(`/data/hotspot`).then(success).catch(fail);
}

export { getHotspots };
