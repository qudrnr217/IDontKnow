import { apiInstanceWithoutAuthorization } from "./index.js";

const api = apiInstanceWithoutAuthorization();

function regist(user, success, fail) {
  user.age = parseInt(user.age);
  api.post(`/users/join`, user).then(success).catch(fail);
}

function checkEmailDuplication(email, success, fail) {
  api.get(`/users/email/${email}/exist`).then(success).catch(fail);
}

function checkNameDuplication(name, success, fail) {
  api.get(`/users/name/${name}/exist`).then(success).catch(fail);
}

function login(user, success, fail) {
  api.post(`/users/login`, user).then(success).catch(fail);
}

function resetPassword(email, success, fail) {
  api.patch(`/users/pw`, email).then(success).catch(fail);
}

export {
  regist,
  checkEmailDuplication,
  checkNameDuplication,
  login,
  resetPassword,
};
