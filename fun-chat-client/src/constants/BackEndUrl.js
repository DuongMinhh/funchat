export const API_BASE_URL = 'http://localhost:8080/fun-chat/api'
export const OAUTH2_REDIRECT_URI = 'http://localhost:3000/oauth2/redirect'

export const GOOGLE_AUTH_URL = API_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = API_BASE_URL + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL = API_BASE_URL + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;

const BE_USER_URL = '/user'
export const BE_USER_ME_URL = `${API_BASE_URL}${BE_USER_URL}/me`
export const BE_USER_LEAVE_ROOM = `${API_BASE_URL}${BE_USER_URL}/leave/room`
export const BE_USER_SEARCH_URL = `${API_BASE_URL}${BE_USER_URL}/search`

const BE_ROOM_URL = '/room'
export const BE_ROOM_BASE_URL = `${API_BASE_URL}${BE_ROOM_URL}`
export const BE_ROOM_ALL_URL = `${API_BASE_URL}${BE_ROOM_URL}/all`

export const BE_AUTH_URL = '/auth'
export const BE_AUTH_LOGIN_URL = `${API_BASE_URL}${BE_AUTH_URL}/login`
export const BE_AUTH_SIGNUP_URL = `${API_BASE_URL}${BE_AUTH_URL}/signup`

const BE_MESSAGE_URL = '/message'
export const BE_MESSAGE_BASE_URL = `${API_BASE_URL}${BE_MESSAGE_URL}`
export const BE_MESSAGE_ALL_URL = `${API_BASE_URL}${BE_MESSAGE_URL}/all`
export const BE_MESSAGE_COUNT_PAGE_URL = `${API_BASE_URL}${BE_MESSAGE_URL}/count-page`

export const BE_WEBSOCKET_CLIENT_URL = `${API_BASE_URL}/ws`