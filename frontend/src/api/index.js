import axios from "axios";
import { API_BASE_URL } from "@/config";

// access-token이 필요없는 axios 객체 생성
function apiInstanceWithoutAuthorization() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      Accept: "application/json",
    },
  });
  return instance;
}

export { apiInstanceWithoutAuthorization };
