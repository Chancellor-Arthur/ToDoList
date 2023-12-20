import axios from 'axios';

export const logout = () => {
	return axios
		.post(
			`http://${process.env.NEXT_PUBLIC_HOST}/auth/logout`,
			{ },
			{
				headers: {
					'Content-Type': 'application/json; charset=utf-8',
				},
			}
		)
		.then((res) => res.data);
};
