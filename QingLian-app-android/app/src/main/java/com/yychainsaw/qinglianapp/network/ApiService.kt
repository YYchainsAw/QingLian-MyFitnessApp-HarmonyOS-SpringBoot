package com.yychainsaw.qinglianapp.network

import com.yychainsaw.qinglianapp.data.model.*
import com.yychainsaw.qinglianapp.data.model.dto.*
import com.yychainsaw.qinglianapp.data.model.vo.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    // ================= 1. 用户认证模块 (Auth) =================
    @POST("/auth/register")
    suspend fun register(@Body registerDto: UserRegisterDTO): ApiResponse<Void>

    @POST("/auth/login")
    suspend fun login(@Body loginDto: UserLoginDTO): ApiResponse<TokenVO>

    @POST("/auth/logout")
    suspend fun logout(): ApiResponse<Void>

    @POST("/auth/refresh")
    suspend fun refreshToken(): ApiResponse<String> // 返回新 Token

    // ================= 2. 用户个人中心 (User) =================
    @GET("/user/info")
    suspend fun getUserInfo(): ApiResponse<UserVO>

    @PUT("/user/update")
    suspend fun updateUserInfo(@Body updateDto: UserUpdateDTO): ApiResponse<Void>

    @DELETE("/user/delete")
    suspend fun deleteUser(): ApiResponse<Void>

    @GET("/user/search")
    suspend fun searchUsers(@Query("keyword") keyword: String): ApiResponse<List<UserVO>>

    @GET("/user/dashboard")
    suspend fun getUserDashboard(): ApiResponse<UserSocialDashboardVO>

    // ================= 3. 社交与聊天 (Friendship & Message) =================
    @POST("/friendships/request")
    suspend fun sendFriendRequest(@Query("friendId") friendId: String): ApiResponse<Void>

    @PUT("/friendships/{friendId}/accept")
    suspend fun acceptFriendRequest(@Path("friendId") friendId: String): ApiResponse<Void>

    @DELETE("/friendships/{friendId}")
    suspend fun deleteFriend(@Path("friendId") friendId: String): ApiResponse<Void>

    @GET("/friendships/plans")
    suspend fun getFriendPlans(): ApiResponse<List<FriendPlanVO>>

    @GET("/friendships/rankings")
    suspend fun getFriendRankings(): ApiResponse<List<FriendRankingVO>>

    @POST("/messages")
    suspend fun sendMessage(@Body messageDto: MessageSendDTO): ApiResponse<MessageVO>

    @PUT("/messages/read/{senderId}")
    suspend fun markMessagesRead(@Path("senderId") senderId: String): ApiResponse<Void>

    @GET("/messages/unread/count")
    suspend fun getUnreadMessageCount(): ApiResponse<Long>

    @GET("/messages/history/{friendId}")
    suspend fun getMessageHistory(@Path("friendId") friendId: String): ApiResponse<List<MessageVO>>

    // ================= 4. 健身动作库 (Movement) =================
    @POST("/movements/add")
    suspend fun addMovement(@Body movementDto: MovementDTO): ApiResponse<Void>

    @GET("/movements/search")
    suspend fun searchMovements(
        @Query("keyword") keyword: String?,
        @Query("pageNum") pageNum: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): ApiResponse<PageBean<MovementVO>>

    @POST("/movements/change-difficulty")
    suspend fun changeMovementDifficulty(@Body difficultyDto: MovementDifficultyDTO): ApiResponse<Void>

    @GET("/movements/countCategories")
    suspend fun countMovementCategories(): ApiResponse<List<CategoryCountVO>> // 建议后端返回对象列表而非 Map

    @GET("/movements/hardcore")
    suspend fun getHardcoreMovements(): ApiResponse<List<MovementVO>>

    @GET("/movements/analytics")
    suspend fun getMovementAnalytics(): ApiResponse<List<MovementAnalyticsVO>>

    // ================= 5. 健身记录与计划 (Workout & Plan) =================
    @POST("/workout-records/add")
    suspend fun addWorkoutRecord(@Body recordDto: WorkoutRecordDTO): ApiResponse<Void>

    @POST("/workout-records/log-by-movement")
    suspend fun logWorkoutByMovement(@Body logDto: LogWorkoutByMovementDTO): ApiResponse<Void>

    @GET("/workout-records/history")
    suspend fun getWorkoutHistory(): ApiResponse<List<WorkoutRecordDTO>>

    @GET("/workout-records/today-calories")
    suspend fun getTodayCalories(): ApiResponse<Int>

    @GET("/workout-records/leaderboard")
    suspend fun getBurnLeaderboard(): ApiResponse<List<BurnRankVO>>

    @POST("/plans")
    suspend fun createPlan(@Body planDto: PlanCreateDTO): ApiResponse<Void>

    @GET("/plans/active")
    suspend fun getActivePlans(): ApiResponse<List<PlanVO>>

    @PUT("/plans/{planId}/complete")
    suspend fun completePlan(@Path("planId") planId: String): ApiResponse<Void>

    // ================= 6. 社区动态 (Community) =================
    @POST("/community/posts")
    suspend fun createPost(@Body postDto: PostCreateDTO): ApiResponse<Void>

    @GET("/community/feed")
    suspend fun getFeed(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): ApiResponse<PageBean<PostVO>>

    @POST("/community/posts/{postId}/like")
    suspend fun likePost(@Path("postId") postId: String): ApiResponse<Void>

    @GET("/community/influencers")
    suspend fun getInfluencers(): ApiResponse<List<InfluencerVO>>

    @GET("/community/recommend-friends")
    suspend fun getRecommendFriends(): ApiResponse<List<PotentialFriendVO>>

    @Multipart
    @POST("upload") // 确保路径与后端一致
    suspend fun upload(@Part file: MultipartBody.Part): ApiResponse<String>

    @Multipart
    @POST("upload/batch")
    suspend fun uploadBatch(@Part files: List<MultipartBody.Part>): ApiResponse<List<String>>
}
