import axios from 'axios';

export const registration = (username: string, password: string, confirm: string)=> {
	return axios
		.post(
			`http://${process.env.NEXT_PUBLIC_HOST}/auth/registration`,
			JSON.stringify({ username:username, password:password, confirmPassword:confirm}),
			{
				headers: {
					'Content-Type': 'application/json; charset=utf-8',
				},
			}
		)
		.then((res) => res.data);
};
