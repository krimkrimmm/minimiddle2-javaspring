//API Admin Movie
//1. Lấy danh sách phim
//API
//GET /api/admin/movies?page={page}&pageSize={pageSize}
//
//Mô tả
//API dùng để lấy danh sách phim theo phân trang.
//Tham số:
//page: số trang, mặc định là 1
//pageSize: số lượng phần tử trên mỗi trang, mặc định là 10
//Yêu cầu
//Viết API để hiển thị danh sách phim ra giao diện admin.
//Kiểm tra với nhiều giá trị khác nhau của page và pageSize để đảm bảo phân trang hoạt động đúng.
//2. Tạo mới phim
//API
//POST /api/admin/movies
//
//Mô tả
//API dùng để tạo mới một phim.
//Dữ liệu gửi lên gồm các trường như tên phim, mô tả, trailer, diễn viên, đạo diễn, thể loại, năm phát hành, loại phim, quốc gia và trạng thái.
//Body mẫu:
//
//{
//  "name": "Tên phim",
//  "trailerUrl": "https://trailer.url",
//  "description": "Mô tả phim",
//  "genreIds": [1, 2],
//  "actorIds": [3, 4],
//  "directorIds": [5],
//  "releaseYear": 2024,
//  "type": "PHIM_LE",
//  "status": true,
//  "countryId": 1
//}
//
//Yêu cầu
//Viết API để tạo phim mới từ form hoặc dữ liệu JSON.
//Áp dụng validate đầu vào với các quy tắc sau:
//name: không được để trống (@NotEmpty)
//trailerUrl: không được để trống (@NotEmpty)
//description: không được để trống (@NotEmpty)
//releaseYear: không được null (@NotNull)
//type: không được null (@NotNull) – là enum MovieType
//status: không được null (@NotNull)
//countryId: không được null (@NotNull)
//genreIds, actorIds, directorIds: có thể rỗng nhưng nên là danh sách số nguyên.
//Kiểm thử các trường hợp:
//Gửi đầy đủ → tạo thành công
//Bỏ trống các trường bắt buộc → trả lỗi validate chi tiết
//Truyền sai kiểu dữ liệu (ví dụ releaseYear là chuỗi) → trả lỗi định dạng
//Truyền type sai giá trị enum → trả lỗi parse enum
//3. Cập nhật phim
//API
//PUT /api/admin/movies/{id}
//
//Mô tả
//API dùng để cập nhật thông tin của một phim đã có.
//Gửi id phim qua URL path, dữ liệu cập nhật giống như khi tạo mới phim.
//Body mẫu: (giống tạo mới)
//
//Yêu cầu
//Viết API cập nhật phim dựa trên id.
//Áp dụng validate tương tự như khi tạo mới phim.
//Kiểm thử các trường hợp:
//Cập nhật thành công với id và dữ liệu hợp lệ
//Thiếu trường → lỗi validate
//id không tồn tại → xử lý và hiển thị lỗi rõ ràng
//4. Xóa phim
//API
//DELETE /api/admin/movies/{id}
//
//Mô tả
//API dùng để xóa phim theo id.
//Sau khi gọi thành công, phim sẽ không còn trong danh sách.
//Yêu cầu
//Viết hàm xoá phim dựa theo id.
//Kiểm thử các trường hợp:
//Xoá thành công với id hợp lệ
//Xoá với id không tồn tại: xử lý và thông báo lỗi phù hợp
//API Admin Episode
//1. Lấy danh sách tập phim
//API
//GET /api/admin/episodes?movieId={movieId}&page={page}&pageSize={pageSize}
//
//Mô tả
//API dùng để lấy danh sách các tập phim của một phim cụ thể theo phân trang.
//Tham số:
//movieId: ID của phim cần lấy danh sách tập
//page: số trang, mặc định là 1
//pageSize: số lượng phần tử trên mỗi trang, mặc định là 10
//Yêu cầu
//Viết API để hiển thị danh sách tập phim của một bộ phim cụ thể trên giao diện admin.
//Kiểm thử với các trường hợp:
//movieId hợp lệ → trả về danh sách tập phim đúng
//movieId không tồn tại→ thông báo lỗi phù hợp
//Thay đổi page và pageSize → đảm bảo phân trang hoạt động chính xác
//Nếu thiếu movieId → trả lỗi yêu cầu bắt buộc
//2. Tạo mới tập phim
//API
//POST /api/admin/episodes
//
//Mô tả
//API dùng để tạo mới một tập phim.
//Dữ liệu gửi lên gồm các trường như tên tập phim, thứ tự hiển thị, trạng thái, và movieId tương ứng.
//Body mẫu:
//
//{
//  "name": "Tập 1",
//  "displayOrder": 1,
//  "status": true,
//  "movieId": 101
//}
//
//Yêu cầu
//Viết API để tạo mới tập phim từ dữ liệu đầu vào.
//Áp dụng validate bắt buộc các trường thông tin:
//name: không được để trống
//displayOrder, status, movieId: không được null
//movieId: Tồn tại trong hệ thống
//Kiểm thử các trường hợp:
//Dữ liệu đầy đủ → tạo thành công
//Thiếu dữ liệu → hiển thị lỗi hợp lệ
//3. Cập nhật tập phim
//API
//PUT /api/admin/episodes/{id}
//
//Mô tả
//API dùng để cập nhật thông tin của một tập phim.
//Gửi id qua đường dẫn URL. Các trường được cập nhật gồm name, displayOrder, status.
//Body mẫu:
//
//{
//  "name": "Tập 1 - bản cập nhật",
//  "displayOrder": 2,
//  "status": false
//}
//
//Yêu cầu
//Viết API để cập nhật tập phim dựa trên id.
//Áp dụng validate cho các trường:
//name: không được để trống
//displayOrder, status: không được null
//Kiểm thử:
//Cập nhật thành công
//id không tồn tại → xử lý lỗi
//Dữ liệu thiếu → hiển thị lỗi validate
//4. Xóa tập phim
//API
//DELETE /api/admin/episodes/{id}
//
//Mô tả
//API dùng để xóa một tập phim theo id.
//Sau khi xóa thành công, tập phim sẽ không còn trong danh sách.
//Yêu cầu
//Viết API để xóa tập phim với id được truyền qua URL.
//Kiểm thử:
//Xóa thành công với id hợp lệ
//Xóa với id không tồn tại → xử lý lỗi và thông báo rõ ràng





















package vn.scrip.middle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class MiddleApplication {
	public static void main(String[] args) {
		SpringApplication.run(MiddleApplication.class, args);
	}
}
