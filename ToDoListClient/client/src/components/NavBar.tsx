'use client';
import Box from '@mui/material/Box';
import ListAltIcon from '@mui/icons-material/ListAlt';
import LoginIcon from '@mui/icons-material/Login';
import AppRegistrationIcon from '@mui/icons-material/AppRegistration';
import Link from 'next/link';
import { Button, Theme, styled } from '@mui/material';
import { useUser } from "@store";

const NavBar = () => {
	const username = useUser((user) => user.username);
	const logoutZustand = useUser((user) => user.logoutUser);

	return (
		<StyledBox>
			<Link href="/">
				<Button>
					<ListAltIcon />
					ToDo List
				</Button>
			</Link>
			{!username &&
				<div>
					<Link href="/login">
						<Button>
							Войти
							<LoginIcon />
						</Button>
					</Link>
					<Link href="/registration">
						<Button>
							Регистрация
							<AppRegistrationIcon />
						</Button>
					</Link>
				</div>}
			{username &&
				<Button onClick={logoutZustand}>
					({username}) Выйти
					<LoginIcon />
				</Button>
				}
		</StyledBox>
	);
};

export default NavBar;

const StyledBox = styled(Box)(({ theme }: { theme: Theme }) => ({
	display: 'flex',
	padding: '8px 80px',
	justifyContent: 'space-between',
	backgroundColor: theme.palette.backgroundColors.main,
	boxShadow: `0px 2px ${theme.palette.border.main}`,
	'@media (max-width: 890px)': {
		padding: '8px 10px',
	},
}));
