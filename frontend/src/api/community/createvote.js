import { apiInstanceWithAuthorization } from "./index.js";

function createVotes(token, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.post(`/api/votes`, {}).then(success).catch(fail);
}

export { createVotes };
