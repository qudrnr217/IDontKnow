import { apiInstanceWithAuthorization } from "./index.js";

// 방생성
function createVote(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.post(`/votes`, params).then(success).catch(fail);
}

// 카테고리별 방목록 조회
function showVoteList(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api
    .get(
      `/votes?category=${params.category}&status=${params.status}&lastVoteId=${params.lastVoteId}`
    )
    .then(success)
    .catch(fail);
}

function detailVote(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.get(`/votes/${params.voteId}`).then(success).catch(fail);
}

// 조회수 Top3
function trendVote(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.get(`/votes/top3?category=${params}`).then(success).catch(fail);
}

// 투표 api

function participateVote(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.post(`/ballots`, params).then(success).catch(fail);
}

function nonparticipateVote(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.delete(`/ballots/${params}`).then(success).catch(fail);
}

function participateVoteAge(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.get(`/ballots/votes/${params}/age`).then(success).catch(fail);
}

function participateVoteGender(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.get(`/ballots/votes/${params}/gender`).then(success).catch(fail);
}

function participateVoteLocation(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.get(`/ballots/votes/${params}/district`).then(success).catch(fail);
}

function changVoteStatus(token, path, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.patch(`/votes/${path}`, params).then(success).catch(fail);
}

// 댓글 api

function commentList(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.get(`/comments/${params}`).then(success).catch(fail);
}

function commentCreate(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.post(`/comments`, params).then(success).catch(fail);
}

function commentDelete(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.delete(`/comments/${params}`).then(success).catch(fail);
}

function commentModify(token, params, success, fail) {
  const api = apiInstanceWithAuthorization(token);
  api.patch(`/comments/${params}`).then(success).catch(fail);
}

export {
  trendVote,
  createVote,
  showVoteList,
  detailVote,
  participateVote,
  nonparticipateVote,
  participateVoteAge,
  participateVoteGender,
  participateVoteLocation,
  changVoteStatus,
  commentList,
  commentCreate,
  commentDelete,
  commentModify,
};
