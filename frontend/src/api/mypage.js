import { apiInstanceWithAuthorization } from "./index.js";

function getUserInfo(token, userId, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.get(`/mypage/users/${userId}/info`).then(success).catch(fail);
}

function updateUserInfo(token, userId, districtId, gender, age, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api
    .patch(
      `/mypage/users/${userId}/info?districtId=${districtId}&gender=${gender}&age=${age}`
    )
    .then(success)
    .catch(fail);
}

function logout(token, userId, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.get(`/mypage/users/${userId}/logout`).then(success).catch(fail);
}

function deleteUserInfo(token, userId, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.patch(`/mypage/users/${userId}`).then(success).catch(fail);
}

function changePassword(token, userId, password, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.patch(`/mypage/users/${userId}/pw`, password).then(success).catch(fail);
}

function getRate(token, userId, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.get(`/mypage/users/${userId}/rate`).then(success).catch(fail);
}

export {
  getUserInfo,
  updateUserInfo,
  logout,
  deleteUserInfo,
  changePassword,
  getRate,
};
