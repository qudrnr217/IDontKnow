import { apiInstanceWithAuthorization } from "./index.js";

function createVotes(token, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.post(`/api/votes`, {}).then(success).catch(fail);
}

// function getBestMenus(districtId, success, fail) {
//   api
//     .get(`/data/menu`, { params: { districtId: districtId } })
//     .then(success)
//     .catch(fail);
// }

export { createVotes };
